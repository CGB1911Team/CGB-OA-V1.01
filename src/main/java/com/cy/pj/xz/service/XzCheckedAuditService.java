package com.cy.pj.xz.service;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.xz.vo.XzUserGoodsApplyVo;

public interface XzCheckedAuditService {
	void saveObject(XzUserGoodsApplyVo entity);
	
	public PageObject<XzUserGoodsApplyVo> findCheckedAudit(
			Integer id,Integer pageCurrent);
}
