package com.cy.pj.xz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.xz.dao.XzUncheckedAuditDao;
import com.cy.pj.xz.service.XzUncheckedAuditService;
import com.cy.pj.xz.vo.XzUserGoodsApplyVo;
/**
 * 业务层实现类——把查询出来的数据最终封装成PageObject对象
 * 1）总记录数
 * 2）总页数
 * 3）封装数据
 * @author Administrator
 *
 */
@Service
public class XzUncheckedAuditServiceImpl implements XzUncheckedAuditService {
	@Autowired
	private XzUncheckedAuditDao xzUncheckedAuditDao;
	
	@Override
	public PageObject<XzUserGoodsApplyVo> findUncheckedAudit(Integer id,Integer pageCurrent) {
		if(pageCurrent==null||pageCurrent<1) 
			throw new IllegalArgumentException("页码值无效");
		int rowCount=xzUncheckedAuditDao.getRowCount(id);
		if(rowCount==0) 
			throw new ServiceException("记录已经不存在");
		int pageSize=7;
		int startIndex=(pageCurrent-1)*pageSize;
		List<XzUserGoodsApplyVo> records = xzUncheckedAuditDao.findUncheckedAudit(id,startIndex,pageCurrent);
		return new PageObject<>( pageCurrent, pageSize, rowCount, records);
	}

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
		xzUncheckedAuditDao.insertObject(entity);
	}

	@Override
	public int changeObjects(Integer... ids) {
		// TODO Auto-generated method stub
		return 0;
	}

}
