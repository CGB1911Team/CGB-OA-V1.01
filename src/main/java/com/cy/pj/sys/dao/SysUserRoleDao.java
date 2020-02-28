package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 操作角色     用户角色关系数据
 * @author Administrator
 *
 */
@Mapper
public interface SysUserRoleDao {
	
	
	/**
	 * 基于用户id查找角色id one2many
	 * @param id
	 * @return
	 */
	List<Integer> findRoleIdsByUserId(Integer id);
	
	int deleteObjectsByUserId(Integer userId);
	
	int insertObjects(
			@Param("userId")Integer userId,
			@Param("roleIds")Integer[] roleIds);
	
	@Delete("delete from sys_user_roles where role_id=#{roleId}")
	int deleteObjectsByRoleId(Integer roleId);
}
