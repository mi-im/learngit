package cn.smbms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sun.org.glassfish.gmbal.ParameterNames;

import cn.smbms.pojo.User;

@Mapper
public interface UserMapper {

	int getTotalCount(@Param("userName")String userName,@Param("userRole")Integer userRole);
	
	List<User> findUserPage(@Param("startPage")int startPage,@Param("pageSize")int pageSize
			,@Param("userName")String userName,@Param("userRole")Integer userRole );

	User getUser(User user);
	
	User getUserById(int id);
	
	User getUserByName(String userCode);
	
	void add(User user);
	
	void update(User user);
	
	void delUser(int uid);
}
