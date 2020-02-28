package com.cy.pj.sys.service;

import java.util.Map;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.vo.SysUserDeptVo;

public interface SysUserService {
	
	Map<String,Object> findObjectById(Integer userId) ;
	
	int updatePassword(String password,
					   String newPassword,
					   String cfgPassword);
	
	int updateObject(SysUser entity,Integer[] roleIds);
	
	/**
	 * 保存用户对象
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	int saveObject(SysUser entity,Integer[] roleIds);
	
	int validById(Integer id,Integer valid,String modifiedUser);
	
	/**
	 * 基于条件查询用户及用户的部门信息，并封装成一个SysUserDeptVo对象
	 * @param username
	 * @param pageCurrent
	 * @return
	 */
	PageObject<SysUserDeptVo> findPageObjects(
			String username,Integer pageCurrent);
}
