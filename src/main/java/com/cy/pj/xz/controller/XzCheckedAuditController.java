package com.cy.pj.xz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.xz.service.XzCheckedAuditService;
import com.cy.pj.xz.vo.XzUserGoodsApplyVo;

@RestController
@RequestMapping("/audit/")
public class XzCheckedAuditController {

	@Autowired
	private XzCheckedAuditService xzCheckedAuditService;
	@RequestMapping("findCheckedAudit")
	@ResponseBody
	public JsonResult findCheckedAudit(Integer id,Integer pageCurrent) {
		PageObject<XzUserGoodsApplyVo> pageObj = xzCheckedAuditService.findCheckedAudit(id, pageCurrent);
		return new JsonResult(pageObj);
		
	}
}
