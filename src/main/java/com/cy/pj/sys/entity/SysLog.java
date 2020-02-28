package com.cy.pj.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * 基于此对象实现日志数据的封装，数据可能来自于
 * 1）数据库（一行记录映射为一个这样的对象）
 * 2）用户行为（通过此对象封装记录用户行为）
 * 
 * 对象可以理解为pojo对象（简单的Java对象），pojo在实际项目中又有很多分类
 * 1）PO Persistent Object 持久化对象（特点，与表中字段有一一对应关系）
 * 2） VO value Object 值对象  根据业务要求定义，不需要与表字段一一对应
 * 
 * 在java所有用于存储数据的对象建议实现Serializable接口,并手动添加一个序列化id
 * HttpSession
 * 
 * @author Administrator
 *
 */

//@Data
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SysLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3353677502856997633L;
	//private static final long serialVersionUID = 1L;
	
	private Integer id;
	//用户名
	private String username;
	//用户操作
	private String operation;
	//请求方法
	private String method;
	//请求参数
	private String params;
	//执行时长(毫秒)
	private Long time;
	//IP地址
	private String ip;
	//创建时间
	private Date createdTime;

}
