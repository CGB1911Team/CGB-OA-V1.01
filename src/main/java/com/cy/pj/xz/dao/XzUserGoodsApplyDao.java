package com.cy.pj.xz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.xz.entity.XzGoods;
import com.cy.pj.xz.entity.XzType;
import com.cy.pj.xz.vo.XzUserGoodsApplyVo;

/**
 * 操作员工用品申请表,处理申请及查看申请业务
 * @author Administrator
 *
 */
@Mapper
public interface XzUserGoodsApplyDao {


	/**
	 * 员工进行申请
	 * 
	 */
	public int insertApply(XzUserGoodsApplyVo xzUserGoodsApplyVo);

	/**
	 * 查找所有物品类别
	 */
	@Select("select * from xz_type")
	public List<XzType> findAllTypes();

	/**
	 * 根据物品类别,查找相关的所有物品
	 */
	@Select("select * from xz_goods where typeId=#{typeId} and repertoryNumber > occupiedNumber")
	public List<XzGoods>  findGoodsByTypeId(Integer typeId);

	/**
	 * 查找所有待审批的申请
	 */
//	@Select("select * from xz_user_goods_apply where status=0 ")
	public  List<XzUserGoodsApplyVo> findUncheckedApply(Integer id);
	/**
	 * 查找所有已审批的申请
	 */
//	@Select("select * from xz_user_goods_apply where status=1 or status=-1")
	public  List<XzUserGoodsApplyVo> findcheckedApply(Integer id);
	
	
	
}
