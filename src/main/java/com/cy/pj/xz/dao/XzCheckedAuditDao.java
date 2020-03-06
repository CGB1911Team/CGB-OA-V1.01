package com.cy.pj.xz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.xz.vo.XzUserGoodsApplyVo;

@Mapper
public interface XzCheckedAuditDao {
	int insertObject(XzUserGoodsApplyVo entity);
	int getRowCount(@Param("id") Integer id);
	public List<XzUserGoodsApplyVo> findCheckedAudit(
			Integer id,Integer startIndex,Integer pageSize) ;
}
