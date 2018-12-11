package cn.smbms.service;

import java.util.List;

import cn.smbms.pojo.User;

public interface UserService {
		int getTotalCount(String userName,Integer userRole);
		
		List<User> findUserPage(int startPage,int pageSize,String userName,Integer userRole);

		User getUser(User user);
		
		User getUserById(int id);
		
		User getUserByName(String userCode);
		
		void saveUser(User user);
		
		void updateUser(User user);
		
		void delUser(int uid);
		
}
