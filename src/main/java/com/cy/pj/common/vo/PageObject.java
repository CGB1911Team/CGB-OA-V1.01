package com.cy.pj.common.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * VO：
 * 业务值对象定义，基于此对象封装数据层返回的数据以及计算的分页信息
 * @author Administrator
 *
 * @param <T>
 */

@NoArgsConstructor
@Setter
@Getter
@ToString
public class PageObject<T> implements Serializable {

	private static final long serialVersionUID = -3432077811313008292L;
	
	/*当前页的页码值*/
	private Integer pageCurrent=1;
	/*页面大小*/
	private Integer pageSize=3;
	/*总行数*/
	private Integer rowCount=0;
	/*总页数*/
	private Integer pageCount=0;
	/*当前页记录*/
	private List<T> records;
	
	public PageObject(Integer pageCurrent, Integer pageSize, Integer rowCount, List<T> records) {
		super();
		this.pageCurrent = pageCurrent;
		this.pageSize = pageSize;
		this.rowCount = rowCount;
		this.records = records;
		//计算总页数
		this.pageCount=(rowCount-1)/pageSize+1;
	}

	
}
