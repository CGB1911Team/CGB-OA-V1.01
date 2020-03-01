package com.cy.pj.xz.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.util.ShiroUtils;
import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.xz.service.XzUserGoodsApplyService;

@RestController
@RequestMapping("/apply/")
public class XzUserGoodsApplyConteoller {
	
	@Autowired
	private XzUserGoodsApplyService xzUserGoodsApplyService;
	
	   @RequestMapping("doApply")
	   public JsonResult doApply(Integer goodsId, Integer applyNumber, String applyDesc){
		   //1.获取用户id
		   Integer userId = ShiroUtils.getUser().getId();
		   xzUserGoodsApplyService.insertApply(userId, goodsId, applyNumber, applyDesc);
		   return new JsonResult("申请成功");
	   }

	   @RequestMapping("findAllTypes")
	   public JsonResult findAllTypes() {
		return new JsonResult(xzUserGoodsApplyService.findAllTypes());
	   }
	   
	   @RequestMapping("findGoods")
	   public JsonResult findGoods(Integer typeId) {
		   return new JsonResult(xzUserGoodsApplyService.findGoodsByTypeId(typeId));
	   }
	   
	   @RequestMapping("findUncheckedApply")
	   public JsonResult findUncheckedApply(Integer userId) {
		   return new JsonResult(xzUserGoodsApplyService.findUncheckedApply(userId));
	   }
	   @RequestMapping("findcheckedApply")
	   public JsonResult findcheckedApply(Integer userId) {
		   return new JsonResult(xzUserGoodsApplyService.findcheckedApply(userId));
	   }
	   
}
