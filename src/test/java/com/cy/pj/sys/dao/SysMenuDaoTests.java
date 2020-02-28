package com.cy.pj.sys.dao;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SysMenuDaoTests {
	
	@Autowired
	public SysMenuDao sysMenuDao;
	
	@Test
	public void testFindObjects() {
		List<Map<String, Object>> list = sysMenuDao.findObject();
		//List<Map<String, Object>> list = null;
		//Assertions.assertNotEquals(null,list);//断言测试（了解）list为null不会向下执行
		System.out.println("list.size="+list.size());
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
		//jdk8  lambda表达式
		list.forEach((map)->System.out.println(map));
		
	}
}
