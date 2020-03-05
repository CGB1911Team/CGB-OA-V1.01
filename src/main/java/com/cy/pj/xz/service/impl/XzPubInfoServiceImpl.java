package com.cy.pj.xz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.xz.dao.XzPubInfoDao;
import com.cy.pj.xz.entity.XzPubInfo;
import com.cy.pj.xz.service.XzPubInfoService;
@Service
public class XzPubInfoServiceImpl implements XzPubInfoService {
	@Autowired
	private XzPubInfoDao xzPubInfoDao;
	/**
	 * 查询所有公告信息
	 */
	@Override
	public List<XzPubInfo> findPubInfo() {
		List<XzPubInfo> list = xzPubInfoDao.findPubInfo();
		if(list==null||list.size()==0)
			throw new ServiceException("暂时没有公告信息！");
		return list;
	}
	/**
	 * 添加公告信息
	 * @return
	 */
	@Override
	public int insertPubInfo(XzPubInfo entity) {
		if(entity==null)
			throw new IllegalArgumentException("参数不能为空");
		if(StringUtils.isEmpty(entity.getArticle()))
			throw new ServiceException("内容不能为空");
		int rows = xzPubInfoDao.insertPubInfo(entity);
		if(rows==0)
			throw new ServiceException("添加失败");
		return rows;
	}

}
