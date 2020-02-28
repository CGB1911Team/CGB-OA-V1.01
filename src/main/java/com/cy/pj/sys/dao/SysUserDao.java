package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.vo.SysUserDeptVo;

@Mapper
public interface SysUserDao {
	
	@Select("select * from sys_users where username=#{username}")
	SysUser findUserByUserName(String username);
	/**
	 * 基于用户id,获取用户以及用户对应的部门信息
	 * @param id
	 * @return
	 */
	SysUserDeptVo findObjectById(Integer id);
	
	int updatePassword(
			@Param("password")String password,
			@Param("salt")String salt,
			@Param("id")Integer id);
	
	int updateObject(SysUser entity);
	
	int insertObject(SysUser entity);
	
	/**
	 * 基于用户id修改用户状态
	 * @param id 用户id
	 * @param valid 用户状态
	 * @param modifiedUse 修改用户
	 * @return
	 */
	int validById(
			@Param("id") Integer id,
			@Param("valid") Integer valid,
			@Param("modifiedUser")String modifiedUser);
	
	/**
	 * 按条件统计用户记录总数
	 * @param username
	 * @return
	 */
	int getRowCount(@Param("username") String username);
	
	/**
	 * 基于条件查询当前页用户信息及对应的部门信息
	 * 1)方案一：业务层向数据层发起多次查询请求，然后进行数据封装
	 * 2）方案二：数据层通过嵌套查询（先查询一张表，可以基于结果再次查询其他表）
	 * 3）方案三：通过多表关联查询，查询需要的数据，(left) join on
	 * @param username
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<SysUserDeptVo> findPageObjects(
			@Param("username")String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
}
