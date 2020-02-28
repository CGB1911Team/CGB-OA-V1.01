
package com.cy.pj.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;

/**
 * 业务层实现类——把查询出来的数据最终封装成PageObject对象
 * 1）总记录数
 * 2）总页数
 * 3）封装数据
 * @author Administrator
 *
 */
@Service
public class SysLogServiceImpl implements SysLogService {

	@Autowired
	private SysLogDao sysLogDao;
	
	@Override
	public PageObject<SysLog> findPageObjects(String name, Integer pageCurrent) {

		if(pageCurrent==null||pageCurrent<1) 
			throw new IllegalArgumentException("页码值无效");
		int rowCount=sysLogDao.getRowCount(name);
		if(rowCount==0) 
			throw new ServiceException("记录已经不存在");
		int pageSize=7;
		int startIndex=(pageCurrent-1)*pageSize;

		List<SysLog> records = sysLogDao.findPageObjects(name, startIndex, pageSize);

		return new PageObject<>( pageCurrent, pageSize, rowCount, records);
	}

	@Override
	public int deleteObjects(Integer... ids) {
		if(ids==null||ids.length==0)  throw new IllegalArgumentException("请选择一条日志信息");
		int rows;
		try {
			rows=sysLogDao.deleteObjects(ids);
		}catch (Throwable e) {
			e.printStackTrace();
			throw new ServiceException("系统故障，正在恢复中。。。。");
		}
		
		if(rows==0)
			throw new ServiceException("记录可能已经不存在！");
		
		return rows;
	}

	@Async
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void saveObject(SysLog entity) {
		String tname=Thread.currentThread().getName();
		System.out.println("log.service.saveObject.thread.name="+tname);
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		sysLogDao.insertObject(entity);
	}

}
