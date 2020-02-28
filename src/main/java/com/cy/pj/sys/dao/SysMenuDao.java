package com.cy.pj.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.hibernate.validator.constraints.ParameterScriptAssert;

import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.entity.SysMenu;

@Mapper
public interface SysMenuDao {
	
	List<String> findPermissions(
			@Param("menuIds") Integer[] menuIds);
	
	/**
	 * 修改 菜单
	 * @param entity
	 * @return
	 */
	int updateObject(SysMenu entity);
	
	/**
	 * 插入菜单数据
	 * 将菜单对象持久化到数据库
	 * @param entity
	 * @return
	 */
	int insertObject(SysMenu entity);
	
	/**
	 * 查询菜单节点信息
	 * @return
	 */
	@Select("select id,name,parentId from sys_menus")
	List<Node> findZtreeMenuNodes();
	
	/**
	 * 基于id统计子菜单的个数
	 * @param id
	 * @return
	 */
	@Select("select count(*) from sys_menus where parentId=#{id}")
	int getChildCount(Integer id);
	
	@Delete("delete from sys_menus where id=#{id}")
	int deleteObject(Integer id);
	/**
	 * 查询所有菜单及上级菜单的名称
	 * 难点：如何获取上级菜单的名称
	 * @return
	 */
	List<Map<String,Object>> findObject();
}
