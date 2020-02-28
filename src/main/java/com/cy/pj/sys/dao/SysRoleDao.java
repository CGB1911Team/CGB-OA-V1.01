package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.vo.SysRoleMenuVo;

@Mapper
public interface SysRoleDao {
	/**
	 * 查询所有角色的id和name
	 * @return
	 */
	List<CheckBox> findObjects();
	
	int updateObject(SysRole entity);
	
	/**
	 * 基于id查询角色以及角色与菜单的关系
	 * @param id
	 * @return
	 */
	SysRoleMenuVo findObjectById(Integer id);
	
	int insertObject(SysRole entity);
	/**
	 * 基于角色id删除角色自身信息
	 * @param id
	 * @return
	 */
	@Delete("delete from sys_roles where id =#{Id}")
	int deleteObject(Integer id);
	
	int getRowCount(String name);
	
	List<SysRole> findPageObjects(
		      @Param("name")String  name,
		      @Param("startIndex")Integer startIndex,
		      @Param("pageSize")Integer pageSize);
}
