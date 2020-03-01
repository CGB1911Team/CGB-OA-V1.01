package com.cy.pj.xz.service;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.xz.vo.XzUserGoodsApplyVo;
/**
 * 查找待审批申请信息
 * @author Administrator
 *
 */
public interface XzUncheckedAuditService {
	void saveObject(XzUserGoodsApplyVo entity);
	/**
	 * 分页查询
	 * @param id 基于条件查询时的参数名
	 * @param pageCurrent 当前的页码值
	 * @return 当前页记录+分页信息
	 */
	public PageObject<XzUserGoodsApplyVo> findUncheckedAudit(
			Integer id,Integer pageCurrent);
	/**
	 * 修改状态实现审批
	 * @param ids
	 * @return
	 */
	int changeObjects(Integer ...ids);
}
