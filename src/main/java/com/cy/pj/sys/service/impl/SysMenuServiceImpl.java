package com.cy.pj.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.common.annotation.RequiredCache;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.service.SysMenuService;


@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	
	@Override
	public List<Map<String, Object>> findObject() {
		List<Map<String, Object>> list = sysMenuDao.findObject();
		if(list==null||list.size()==0) 
			throw new ServiceException("没有对应的菜单信息");
		return list;
	}

	@Override
	public int deleteObject(Integer id) {
		if(id==null||id<1)
			throw new IllegalArgumentException("id值无效");
		int childCount=sysMenuDao.getChildCount(id);
		if(childCount>0)
			throw new ServiceException("请先删除子元素");
		//删除角色，菜单关系数据
		sysRoleMenuDao.deleteObjectsByMenuId(id);
		int rows = sysMenuDao.deleteObject(id);
		if(rows==0) 
			throw new ServiceException("记录可能不存在");
		
		return rows;
	}

	@RequiredCache
	@Override
	public List<Node> findZtreeMenuNodes() {
		List<Node> list = sysMenuDao.findZtreeMenuNodes();
		return list;
	}

	@Override
	public int saveObject(SysMenu entity) {
		if(entity==null) 
			throw new ServiceException("保存对象不能为空");
		
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("菜单名不能为空");
		int rows;
		try {
			rows=sysMenuDao.insertObject(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("保存失败");
		}
		return rows;
	}

	@Override
	public int updateObject(SysMenu entity) {
		if(entity==null)
			throw new ServiceException("保存对象不能为空！");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("菜单名不能为空");
		
		int rows = sysMenuDao.updateObject(entity);
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");
		
		return rows;
	}

}
