package com.cy.pj.common.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class JsonResult implements Serializable {

	private static final long serialVersionUID = 2080943344863436345L;
	
	private int state=1; //1表示ok,0表示error
	private String message="ok";
	private Object data;//此属性一般用于存储业务层返回给控制层的数据
	
	public JsonResult(String message){
		this.message=message;
	}
	public JsonResult(Object data) {
		this.data=data;
	} 
	public JsonResult(Throwable t){
		this.state=0;
		this.message=t.getMessage();
	}
	
	
}
