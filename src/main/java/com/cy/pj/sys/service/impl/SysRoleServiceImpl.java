package com.cy.pj.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.service.SysRoleService;
import com.cy.pj.sys.vo.SysRoleMenuVo;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;
	
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	
	@Override
	public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {
		
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页码值无效");
		int rowCount = sysRoleDao.getRowCount(name);
		if(rowCount==0)
			throw new ServiceException("没有找到对应的记录");
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysRole> records =
			sysRoleDao.findPageObjects(name,startIndex,pageSize);
		return new PageObject<>(pageCurrent,pageSize,rowCount,records);
	}

	/**
	 * 基于id删除角色
	 */	
	@Override
	public int deleteObject(Integer id) {
		if(id==null||id<1)
			throw new IllegalArgumentException("角色id无效");
		sysRoleMenuDao.deleteObjectsByRoleId(id);
		sysUserRoleDao.deleteObjectsByRoleId(id);
		int rows = sysRoleDao.deleteObject(id);
		if(rows==0)
			throw new ServiceException("当前角色可能不存在");		
		return rows;
	}

	@Override
	public int saveObject(SysRole entity, Integer[] menuIds) {
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new IllegalArgumentException("角色名不能为空");
		if(menuIds==null||menuIds.length==0)
			throw new ServiceException("必须为角色分配权限");
		int rows = sysRoleDao.insertObject(entity);
		sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
		
		return rows;
	}

	//Map<String,Map<SympleKey,Object>
	@Cacheable(value="roleCache")
	@Override
	public SysRoleMenuVo findObjectById(Integer id) {
		//1.合法性验证
    	if(id==null||id<=0)
    	throw new ServiceException("id的值不合法");
    	//2.执行查询
    	SysRoleMenuVo result=sysRoleDao.findObjectById(id);
    	//3.验证结果并返回
    	if(result==null)
    	throw new ServiceException("此记录已经不存在");
    	return result;

	}

	@CacheEvict(value="roleCache",key="#entity.id")
	@Override
	public int updateObject(SysRole entity, Integer[] menuIds) {
		if(entity==null)
			throw new IllegalArgumentException("更新的对象不能为空");
		if(entity.getId()==null)
			throw new ServiceException("id的值不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("角色名不能为空");
		if(menuIds==null||menuIds.length==0)
			throw new ServiceException("必须为角色指定一个权限");
		int rows = sysRoleDao.updateObject(entity);
		if(rows==0)
			throw new ServiceException("对象可能已经不存在！");
		sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());
		sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
		
		return rows;
	}

	@Override
	public List<CheckBox> findRoles() {
		return sysRoleDao.findObjects();
	}

}
