package cn.smbms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.smbms.dao.RoleMapper;
import cn.smbms.pojo.Role;
import cn.smbms.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public List<Role> findRoleAll() {
		// TODO Auto-generated method stub
		return roleMapper.findRoleAll();
	}

}
