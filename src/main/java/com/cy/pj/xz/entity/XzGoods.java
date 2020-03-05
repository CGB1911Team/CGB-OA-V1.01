package com.cy.pj.xz.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class XzGoods implements Serializable {

	
	private static final long serialVersionUID = -1420096570099009673L;
	private Integer id;
	private Integer typeId;
	private String 	name;
	private Integer repertoryNumber;
	private Integer occupiedNumber;
	private Date 	createdTime;
	private Date 	modifiedTime;
	
	
}
