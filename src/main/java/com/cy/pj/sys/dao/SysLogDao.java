package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.sys.entity.SysLog;

@Mapper
public interface SysLogDao {
	
	int insertObject(SysLog entity);
	
	/**
	 * 基于条件查询总记录数
	 * 
	 * @Param("username") 新版本不需要加上参数注解，加上是为了向下兼容，最好加上。 
	 * @param username 查询条件（基于用户名称查询）
	 * @return 总记录数
	 */
	int getRowCount(@Param("username") String username);
	
	/**
	 * 基于条件分页查询日志信息
	 * @param username 用户名
	 * @param startIndex 当前页的起始位置
	 * @param pageSize  当前页的页面大小
	 * @return 当前页的日志记录信息列表
	 */
	List<SysLog> findPageObjects(
			@Param("username")String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	/**
	 * 删除日志信息
	 * @param ids id的集合/数组
	 * @return
	 */
	int deleteObjects(@Param("ids")Integer... ids);
	
}
