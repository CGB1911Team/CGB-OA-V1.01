package com.cy.pj.xz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.xz.dao.XzCheckedAuditDao;
import com.cy.pj.xz.service.XzCheckedAuditService;
import com.cy.pj.xz.vo.XzUserGoodsApplyVo;
@Service
public class XzCheckedAuditServiceImpl implements XzCheckedAuditService{

	@Autowired
	private XzCheckedAuditDao xzCheckedAuditDao;
	
	@Async
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void saveObject(XzUserGoodsApplyVo entity) {
		// TODO Auto-generated method stub
		String tname=Thread.currentThread().getName();
		System.out.println("log.service.saveObject.thread.name="+tname);
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		xzCheckedAuditDao.insertObject(entity);
	}

	@Override
	public PageObject<XzUserGoodsApplyVo> findCheckedAudit(Integer id, Integer pageCurrent) {
		// TODO Auto-generated method stub
		if(pageCurrent==null||pageCurrent<1) 
			throw new IllegalArgumentException("页码值无效");
		int rowCount=xzCheckedAuditDao.getRowCount(id);
		if(rowCount==0) 
			throw new ServiceException("记录已经不存在");
		int pageSize=7;
		int startIndex=(pageCurrent-1)*pageSize;
		List<XzUserGoodsApplyVo> records = xzCheckedAuditDao.findCheckedAudit(id,startIndex,pageCurrent);
		return new PageObject<>( pageCurrent, pageSize, rowCount, records);
	}

}
