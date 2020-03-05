package com.cy.pj.xz.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * 
 * @author Administrator
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class XzUserGoodsApplyVo implements Serializable {

	private static final long serialVersionUID = -7942098837994327878L;
	
	private Integer id;  		//申请编号
	private Integer goodsId;	//用品编号
	private String  goodsName;	//用品名称
	private String  typeName; 	//用品的品类名称
	private Integer userId;		//员工编号
	private String  userName;	//员工名称
	private String  deptName;	//员工部门
	private String  userMobile;	//员工手机号
	private Integer applyNumber;//申请数量
	private Date 	applyTime;	//申请时间
	private String  applyDesc;	//申请描述
	private Byte    status;		//审核状态
	private Integer checkId;	//审核人编号
	private String 	checkName;	//审核人名称
	private String  checkMobile;//审核人手机号
	private Date 	checkedTime;//审核时间
	private String 	checkedDesc;//审核描述
	
	
	

}
