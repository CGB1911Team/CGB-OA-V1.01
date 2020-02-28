package com.cy.pj.sys.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cy.pj.sys.entity.SysLog;

@SpringBootTest
public class SysLogDaoTests {
	@Autowired
	private SysLogDao sysLogDao;
	
	@Test
	private void testGetRowCount() {
		int rows = sysLogDao.getRowCount("");
		System.out.println("rows"+rows);
	}
	
	@Test
	public void testFindPageObjects() {
		List<SysLog> list = sysLogDao.findPageObjects("", 0, 3);
		for (SysLog l : list) {
			System.out.println(l.toString());
		}
	}
	
	@Test
	public void testDeleteObjects() {
		int rows = sysLogDao.deleteObjects();
		System.out.println(rows);
	}
	
}
