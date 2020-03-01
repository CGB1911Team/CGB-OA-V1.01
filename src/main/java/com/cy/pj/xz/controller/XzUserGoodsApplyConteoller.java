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
	
	   /**
	    * 打开申请时,展示所有的品类
	    * @return
	    */
	   @RequestMapping("findAllTypes")
	   public JsonResult findAllTypes() {
		return new JsonResult(xzUserGoodsApplyService.findAllTypes());
	   }
	   
	   /**
	    * 根据品类id,查找所有用品
	    * @param typeId
	    * @return
	    */
	   @RequestMapping("findGoods")
	   public JsonResult findGoods(Integer typeId) {
		   return new JsonResult(xzUserGoodsApplyService.findGoodsByTypeId(typeId));
	   }
	
		/**
		 * 点击申请,进行申请提交
		 * @param goodsId
		 * @param applyNumber
		 * @param applyDesc
		 * @return
		 */
	   @RequestMapping("doApply")
	   public JsonResult doApply(Integer goodsId, Integer applyNumber, String applyDesc){
		   //1.获取用户id
		   Integer userId = ShiroUtils.getUser().getId();
		   xzUserGoodsApplyService.insertApply(userId, goodsId, applyNumber, applyDesc);
		   return new JsonResult("申请成功");
	   }
	   
	   /**
	    * 查找所有未审核的申请信息
	    * @param userId
	    * @return
	    */
	   @RequestMapping("findUncheckedApply")
	   public JsonResult findUncheckedApply() {
		   Integer userId = ShiroUtils.getUser().getId();
		   return new JsonResult(xzUserGoodsApplyService.findUncheckedApply(userId));
	   }
	   
	   /**
	    * 查找所有已审核的申请信息
	    * @param userId
	    * @return
	    */
	   @RequestMapping("findcheckedApply")
	   public JsonResult findcheckedApply() {
		   Integer userId = ShiroUtils.getUser().getId();
		   return new JsonResult(xzUserGoodsApplyService.findcheckedApply(userId));
	   }
	   
}
