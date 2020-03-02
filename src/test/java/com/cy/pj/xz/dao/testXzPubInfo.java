package com.cy.pj.xz.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cy.pj.xz.entity.XzPubInfo;

@SpringBootTest
public class testXzPubInfo {
	@Autowired
	private XzPubInfoDao xzPubInfoDao;
	@Test
	public void testFindPubInfo() {
		List<XzPubInfo> list = xzPubInfoDao.findPubInfo();
		for (XzPubInfo l : list) {
			System.out.println(l);
		}
	}
	@Test
	public void testInsertPubInfo(XzPubInfo entity) {
		int rows = xzPubInfoDao.insertPubInfo(entity);
		System.out.println(rows);
	}
	
}
