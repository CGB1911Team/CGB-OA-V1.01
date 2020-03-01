package com.cy.pj.xz.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.xz.vo.XzUserGoodsApplyVo;
/**
 * 用于查找所有待审批的申请,实现分页查询
 * @author Administrator
 *
 */
@Mapper
public interface XzUncheckedAuditDao {
	int insertObject(XzUserGoodsApplyVo entity);
	/**
	 * 基于ID查询总记录数
	 * @param id
	 * @return
	 */
	int getRowCount(@Param("id") Integer id);
	/**
	 * 基于ID分页查询
	 * @param id
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<XzUserGoodsApplyVo> findUncheckedAudit(
			Integer id,Integer startIndex,Integer pageSize);
	/**
	 * 状态修改
	 * @param id
	 * @return
	 */
	int changeObjects(Integer... id);
}

