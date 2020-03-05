package com.cy.pj.xz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.xz.dao.XzUserGoodsApplyDao;
import com.cy.pj.xz.entity.XzGoods;
import com.cy.pj.xz.entity.XzType;
import com.cy.pj.xz.service.XzUserGoodsApplyService;
import com.cy.pj.xz.vo.XzUserGoodsApplyVo;

@Service
public class XzUserGoodsApplyServiceImpl implements XzUserGoodsApplyService{

	@Autowired
	private XzUserGoodsApplyDao xzUserGoodsApplyDao;
	
	/**
	 * 根据用户提交的信息,在申请表中提交申请
	 */
	@Override
	public int insertApply(Integer userId, Integer goodsId, Integer applyNumber, String applyDesc) {
		//参数校验
		if(userId==null || userId<0)
			throw new IllegalArgumentException("用户id不正确");
		if(goodsId==null || goodsId<0)
			throw new IllegalArgumentException("物品id不正确");
		if(applyNumber==null || applyNumber<0)
			throw new IllegalArgumentException("请申请正确的物品数量");
		if(StringUtils.isEmpty(applyDesc))
			throw new IllegalArgumentException("申请描述不能为空");
		//封装数据到申请vo中
		XzUserGoodsApplyVo apply = new XzUserGoodsApplyVo();
		apply.setApplyDesc(applyDesc);
		apply.setApplyNumber(applyNumber);
		apply.setGoodsId(goodsId);
		apply.setUserId(userId);
		//通过dao添加申请,并返回值
		int rows = xzUserGoodsApplyDao.insertApply(apply);
		if(rows==0)
			throw new ServiceException("申请失败");
		return rows;
	}
	
	/**
	 * 查找所有物品类别
	 */
	@Override
	public List<XzType> findAllTypes() {
		 List<XzType> list = xzUserGoodsApplyDao.findAllTypes();
		return list;
	}

	/**
	 * 根据物品类别,查找相关的所有物品
	 */
	@Override
	public List<XzGoods> findGoodsByTypeId(Integer typeId) {
		if(typeId==null || typeId<0)
			throw new IllegalArgumentException("品类Id不正确");
		List<XzGoods> list = xzUserGoodsApplyDao.findGoodsByTypeId(typeId);
		return list;
	}
	
	/**
	 * 查找所有未审核的信息
	 */
	@Override
	public PageObject<XzUserGoodsApplyVo> findUncheckedApply(Integer id,String goodsName,Integer pageCurrent) {
		if(pageCurrent==null||pageCurrent<1) 
			throw new IllegalArgumentException("页码值无效");
		int rowCount=xzUserGoodsApplyDao.uncheckedApplyRowCount(id, goodsName);
		if(rowCount==0)
			throw new ServiceException("没有已审批的申请");
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		List<XzUserGoodsApplyVo> list = xzUserGoodsApplyDao.findUncheckedApply(id, goodsName,startIndex, pageSize);
		
		return new PageObject<>(pageCurrent, pageSize, rowCount, list);
	}
	/**
	 * 查找所有已审核的信息
	 */
	@Override
	public PageObject<XzUserGoodsApplyVo> findcheckedApply(Integer id,String goodsName,Integer pageCurrent) {
		if(pageCurrent==null||pageCurrent<1) 
			throw new IllegalArgumentException("页码值无效");
		int rowCount=xzUserGoodsApplyDao.uncheckedApplyRowCount(id, goodsName);
		if(rowCount==0)
			throw new ServiceException("没有已审批的申请");
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		List<XzUserGoodsApplyVo> list = xzUserGoodsApplyDao.findcheckedApply(id,goodsName,startIndex, pageSize);
		
		return new PageObject<>(pageCurrent, pageSize, rowCount, list);
	}

	
	
}
