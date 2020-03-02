package com.cy.pj.xz.entity;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class XzPubInfo {
	private Integer id;
	private String subTitle;
	private String article;
	private String author;
	private Date createdTime;
	private Date modifiedTime;
}
