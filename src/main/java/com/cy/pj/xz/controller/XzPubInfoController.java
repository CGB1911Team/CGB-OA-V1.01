package com.cy.pj.xz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.xz.entity.XzPubInfo;
import com.cy.pj.xz.service.XzPubInfoService;
@RestController
@RequestMapping("/pubInfo/")
public class XzPubInfoController {
	@Autowired
	private XzPubInfoService xzPubInfoService;
	/**
	 * 查询所有公告信息
	 * @return
	 */
	@RequestMapping("doFindPubInfo")
	public JsonResult doFindPubInfo() {
		return new JsonResult(xzPubInfoService.findPubInfo());
	}
	/**
	 * 添加公告
	 */
	@RequestMapping("doInsertPubInfo")
	public JsonResult doInsertPubInfo(XzPubInfo entity) {
		xzPubInfoService.insertPubInfo(entity);
		return new JsonResult("添加成功");
		
	}
}
