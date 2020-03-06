package com.cy.pj.xz.controller;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * 查找待审批申请信息
 * @author Administrator
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.xz.service.XzUncheckedAuditService;
import com.cy.pj.xz.vo.XzUserGoodsApplyVo;
@RestController
@RequestMapping("/unaudit/")
public class XzUncheckedAuditController {

	@Autowired
	private XzUncheckedAuditService xzUncheckedAuditService;
	@RequestMapping("findUncheckedAudit")
	@ResponseBody
	public JsonResult findUncheckedAudit(Integer id,Integer pageCurrent) {
		PageObject<XzUserGoodsApplyVo> pageObject = xzUncheckedAuditService.findUncheckedAudit(id, pageCurrent);
		System.out.println("newyeat");
		return new JsonResult(pageObject);
	}
}
