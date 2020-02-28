package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 操作角色     菜单关系数据
 * @author Administrator
 *
 */
@Mapper
public interface SysRoleMenuDao {
	
	List<Integer> findMenuIdsByRoleIds(
			@Param("roleIds")Integer[] roleId);
	
	@Select("select menu_id from sys_role_menus where role_id=#{id}")
	List<Integer> findMenuIdsByRoleId(Integer roleId);
	
	@Delete("delete from sys_role_menus where menu_id=#{menuId}")
	int deleteObjectsByMenuId(Integer menuId);
	
	@Delete("delete from sys_role_menus where role_id=#{roleId}")
	int deleteObjectsByRoleId(Integer roleId);
	/**
	 * 保存角色和菜单关系数据
	 * @param roleId
	 * @param menuIds
	 * @return
	 */
	int insertObjects(@Param("roleId")Integer roleId,
					@Param("menuIds") Integer[] menuIds);
	
}
