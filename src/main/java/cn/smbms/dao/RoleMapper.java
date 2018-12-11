package cn.smbms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.smbms.pojo.Role;

@Mapper
public interface RoleMapper {
	List<Role> findRoleAll();
}
