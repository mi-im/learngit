package cn.smbms.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.smbms.dao.UserMapper;
import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.RoleService;
import cn.smbms.service.UserService;
import cn.smbms.tools.Constants;
import cn.smbms.tools.ImageUtil;
import cn.smbms.tools.PageSupport;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	//登录页面
	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	//验证码
	@RequestMapping("/valicode.do") // 对应/user/valicode.do请求
	public void valicode(HttpServletResponse response, HttpSession session) throws Exception {
		// 利用图片工具生成图片
		// 第一个参数是生成的验证码，第二个参数是生成的图片
		Object[] objs = ImageUtil.createImage();
		// 将验证码存入Session
		session.setAttribute("imageCode", objs[0]);
		// 将图片输出给浏览器
		BufferedImage image = (BufferedImage) objs[1];
		response.setContentType("image/png");
		OutputStream os = response.getOutputStream();
		ImageIO.write(image, "png", os);
	}
	
	//登录
	@RequestMapping(value="/logindo.do",method=RequestMethod.POST)
	public String logindo(User user,Model model,HttpServletRequest request) {
		User userSession = userService.getUser(user);
		if(userSession==null) {
			model.addAttribute("userCode",user.getUserCode());
			model.addAttribute("error","账号密码错误");
			return "login";
		}
		request.getSession().setAttribute("userSession", userSession);
		return "redirect:/user/userlist.do";
	}
	
	//退出登录
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "login";
	}
	
	//检查验证码
	@RequestMapping("/checkValicode.do")
	@ResponseBody
	public String checkValicode(String valicode,HttpServletRequest request) {
		String code = (String) request.getSession().getAttribute("imageCode");
		if(code.equalsIgnoreCase(valicode)) {
			return "1";
		}
		return "0";
	}
	
	//USER显示
	@RequestMapping("/userlist.do")
	public String listUser(Model model, @RequestParam(value = "queryname", required = false) String queryUserName,
			@RequestParam(value = "queryUserRole", required = false) String queryUserRole,
			@RequestParam(value = "pageIndex", required = false) String pageIndex) {
		int startPage = 1;
		
		if(pageIndex!=null) {
			startPage = Integer.valueOf(pageIndex);
		}
		
		Integer rid = null;
		
		if(queryUserRole!=null && queryUserRole!="") {
			rid = Integer.valueOf(queryUserRole);
		}
		
		
		int pageSize = Constants.pageSize;
		
		PageSupport page = new PageSupport();
		page.setPageSize(pageSize);
		page.setCurrentPageNo(startPage);
		int totalCount = userService.getTotalCount(queryUserName,rid);
		page.setTotalCount(totalCount);
		
		int totalPageCount = page.getTotalPageCount();
		
		List<User> userList = userService.findUserPage((startPage-1)*pageSize, pageSize,queryUserName,rid);
		
		List<Role> roleList = roleService.findRoleAll();
		
		model.addAttribute("userList",userList);
		model.addAttribute("totalPageCount",totalPageCount);
		model.addAttribute("currentPageNo",startPage);
		model.addAttribute("totalCount",totalCount);
		model.addAttribute("roleList",roleList);
		model.addAttribute("queryUserName", queryUserName);
		model.addAttribute("queryUserRole", queryUserRole);
		
		
		return "user/userlist";
	}

	//USER查看
	@RequestMapping("/view.do")
	@ResponseBody
	public User view(int id) {
		User user = userService.getUserById(id);
		return user;
	}


	//添加页面
	@RequestMapping("/addUser.do")
	public String addUser() {
		return "/user/useradd";
	}

	//检查姓名
	@RequestMapping("/ucexist.do")
	@ResponseBody
	public String  checkName(String userCode) {
		if(userCode==null || userCode=="") {
			return "exist";
		}
		
		User user = userService.getUserByName(userCode);
		if(user==null) {
			return "ok";
		}
		return "exist";
	}

	//添加USER
	@RequestMapping(value = "/addUsersave.do")
	public String addUserSave(User user, HttpSession session, HttpServletRequest request,
		@RequestParam MultipartFile[] attachs) {
		for (int i = 0; i < attachs.length; i++) {
			String errorInfo = "uploadFileError";
			
			MultipartFile attach = attachs[i];
			String houzhui = FilenameUtils.getExtension(attach.getOriginalFilename());
			
			if(i==1) {
				errorInfo  = "uploadWpError";
			}
			
			//判断图片不为空
			if(!attach.isEmpty()) {
				//判断是否是图片
				if(!houzhui.equalsIgnoreCase("jpg") &&
						!houzhui.equalsIgnoreCase("jpeg") &&
						!houzhui.equalsIgnoreCase("png") &&
						!houzhui.equalsIgnoreCase("pneg") 
						) {
					request.setAttribute(errorInfo, "* 上传图片格式不正确");
					return "/user/useradd"; 
				}
				
				//判断图片大小
				if (attach.getSize() > 500000) {// 上传大小不得超过 500k
					request.setAttribute(errorInfo, " * 上传大小不得超过 500k");
					return "/user/useradd"; 
				}
				
			}else {
				request.setAttribute(errorInfo, "* 请上传图片");
				return "/user/useradd";
			}
			
			String zhengJianPath = request.getRealPath("/statics/zhengjian/");
			String gongZuoPath =  request.getRealPath("/statics/gongzuo/");
			String path;
			
			if(i==0) {
				path = zhengJianPath;
			}else {
				path = gongZuoPath;
			}
			
			File file = new File(path);
			if(!file.exists()) {
				file.mkdirs();
			}
			String name = UUID.randomUUID().toString().replaceAll("-", "");
			try {
				attach.transferTo(new File(path+name+"."+houzhui));
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				request.setAttribute(errorInfo, "上传失败");
				e.printStackTrace();
				return "/user/useradd";
			}
				
		}
		
		if(null != session.getAttribute("userSession")) {
			user.setCreatedBy(((User) session.getAttribute("userSession")).getId());
		}
		user.setCreationDate(new Date());
		userService.saveUser(user);
		
		return "redirect:/user/userlist.do";
	}

	//修改页面
	@RequestMapping("/usermodify.do")
	public String usermodify(int uid,Model model) {
		User user = userService.getUserById(uid);
		model.addAttribute("user", user);
		return "/user/usermodify";
	}

	//User修改
	@RequestMapping("/usermodifysave.do")
	public String usermodifysave(User user,HttpServletRequest request) {
		User usersseion = (User) request.getSession().getAttribute("userSession");
		if(usersseion!=null) {
			user.setModifyBy(usersseion.getId());
		}
		
		user.setModifyDate(new Date());
			
		
		userService.updateUser(user);
		
		return "redirect:/user/userlist.do";
	}

	//删除USER
	@RequestMapping("/delUser.do")
	@ResponseBody
	public Object delUser(int uid) {
		userService.delUser(uid);
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("delResult", "true");
		return map;
	}
}
