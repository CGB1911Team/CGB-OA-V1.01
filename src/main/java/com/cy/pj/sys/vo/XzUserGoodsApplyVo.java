package com.cy.pj.sys.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class XzUserGoodsApplyVo implements Serializable {

	private static final long serialVersionUID = -7942098837994327878L;
	
	Integer id;  		//申请编号
	Integer goodsId;	//用品编号
	String  goodsName;	//用品名称
	String  typeName; 	//用品的品类名称
	Integer userId;		//员工编号
	String  userName;	//员工名称
	String  deptName;	//员工部门
	String  userMobile;	//员工手机号
	Integer applyNumber;//申请数量
	Date 	applyTime;	//申请时间
	String  applyDesc;	//申请描述
	Byte    status;		//审核状态
	Integer checkId;	//审核人编号
	String 	checkName;	//审核人名称
	String  checkMobile;//审核人手机号
	Date 	checkedTime;//审核时间
	String 	checkedDesc;//审核描述
	
	
	

}
