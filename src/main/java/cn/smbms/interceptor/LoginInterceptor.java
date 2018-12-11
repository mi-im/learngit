package cn.smbms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.smbms.pojo.User;

public class LoginInterceptor implements  HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String uri = request.getRequestURI();
		System.out.println(uri);
		if(!uri.contains("/login") && !uri.contains("/valicode") && !uri.contains("Valicode")) {
			User user = (User) request.getSession().getAttribute("userSession");
			if(user==null) {
				response.sendRedirect(request.getContextPath()+"/user/login.do");
				return false;
			}
		}
		return true;
	}

}
