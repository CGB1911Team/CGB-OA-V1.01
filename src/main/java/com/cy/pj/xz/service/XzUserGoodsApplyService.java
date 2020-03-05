package com.cy.pj.xz.service;

import java.util.List;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.xz.entity.XzGoods;
import com.cy.pj.xz.entity.XzType;
import com.cy.pj.xz.vo.XzUserGoodsApplyVo;

public interface XzUserGoodsApplyService {
	
	public int insertApply(Integer userId,
						   Integer goodsId,
						   Integer applyNumber,
						   String applyDesc);
	
	public List<XzType> findAllTypes();
	
	public List<XzGoods> findGoodsByTypeId(Integer typeId);
	
	public  PageObject<XzUserGoodsApplyVo> findUncheckedApply(Integer id,String goodsName,Integer pageCurrent);
	
	public  PageObject<XzUserGoodsApplyVo> findcheckedApply(Integer id,String goodsName,Integer pageCurrent);
}
