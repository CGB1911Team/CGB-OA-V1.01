package com.cy.pj.common.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 借助此对象封装checkBox对象
 * @author Administrator
 *
 */
@Getter
@Setter
@ToString
public class CheckBox implements Serializable {

	private static final long serialVersionUID = -4981579830254536984L;
	private Integer id;
	private String name;
}
