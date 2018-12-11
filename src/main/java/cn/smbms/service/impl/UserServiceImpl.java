package cn.smbms.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.smbms.dao.UserMapper;
import cn.smbms.pojo.User;
import cn.smbms.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int getTotalCount(String userName,Integer userRole) {
		
		int totalCount = userMapper.getTotalCount(userName,userRole);
		
		return totalCount;
	}

	@Override
	public List<User> findUserPage(int startPage, int pageSize,String userName,Integer userRole) {
		
		List<User> list =userMapper.findUserPage(startPage, pageSize,userName,userRole);
		
		return list;
	}

	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.getUser(user);
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		User user = userMapper.getUserById(id);
		return user;
	}

	@Override
	public User getUserByName(String userCode) {
		// TODO Auto-generated method stub
		return userMapper.getUserByName(userCode);
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userMapper.add(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userMapper.update(user);
	}

	@Override
	public void delUser(int uid) {
		// TODO Auto-generated method stub
		userMapper.delUser(uid);
	}

}
