package com.cy.pj.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysUserService;
import com.cy.pj.sys.vo.SysUserDeptVo;

@Transactional(timeout = 30,
			readOnly = false,
			isolation = Isolation.READ_COMMITTED,
			rollbackFor = Throwable.class,
			propagation = Propagation.REQUIRED)
@Service
//@Slf4j //编写日志
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;

	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	//对外使用规范
	//或者使用@Slf4j
	//private static final Logger logger=LoggerFactory.getLogger(SysUserServiceImpl.class);

	/**
	 * 查询用户及其部门信息
	 * 1、参数校验
	 * 2、查询总记录数并校验
	 * 3、查询当前页要呈现的记录
	 * 4、封装结果并返回
	 */
	/**
	 * 方法上的注解用于定义事务特性，readonly=true一般用于描述查询方法，表示忽略那些不需要执行事务的方法
	 */
	@Transactional(readOnly = true) //事务切面
	@RequiredLog(operation="用户查询") //日志切面
	@Override
	public PageObject<SysUserDeptVo> findPageObjects(String username, Integer pageCurrent) {
		
		String tname=Thread.currentThread().getName();
		System.out.println("log.service.saveObject.thread.name="+tname);
		
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页码值无效！");
		int rowCount = sysUserDao.getRowCount(username);
		if(rowCount==0)
			throw new ServiceException("没有找到对应的记录！");
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysUserDeptVo> records = sysUserDao.findPageObjects(username, startIndex, pageSize);

		return new PageObject<>(pageCurrent, pageSize, rowCount, records);
	}

	/**
	 * 用户禁用与启用
	 * 
	 * 1、参数校验
	 * 2、修改用户状态
	 * 3、返回结果
	 */
	/**
	 * @RequiresPermissions 注解由shiro提供   描述的方法，表示该方法必须授权才能访问
	 * 思考：
	 * 1）为谁进行授权？  登陆用户（已经认证的用户）
	 * 2）为什么给他授权？（认证用户具备访问这个资源权限，才会给他授权）
	 * 3）如何认证用户是否具备这个资源的权限
	 * 3.1)查询用户具备什么权限（用户可以访问哪些菜单--菜单中有一个权限标识字段permission）
	 * 3.2）检测用户拥有的权限标识中是否包含@RequiresPermissions注解中定义的字符串，
	 * 假如有，则认为有权限
	 * 4）由谁授权？ securityManager(这个接口继承了认证和授权的接口)
	 */
	@RequiresPermissions("sys:user:update")
	@RequiredLog(operation="禁用启用")
	@Override
	public int validById(Integer id, Integer valid, String modifiedUser) {
		if(id==null||id<1)
			throw new IllegalArgumentException("参数不合法：id="+id);
		if(valid!=1&&valid!=0)
			throw new IllegalArgumentException("参数不合法：valid="+valid);
		if(StringUtils.isEmpty(modifiedUser))
			throw new ServiceException("修改用户不能为空");
		int rows = sysUserDao.validById(id, valid, modifiedUser);
		if(rows==0)
			throw new ServiceException("此记录可能已经不存在");

		return rows;
	}

	@Transactional
	@RequiredLog(operation="新增用户")
	@Override
	public int saveObject(SysUser entity, Integer[] roleIds) {
	//	log.info("satrt="+System.currentTimeMillis());
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空！");
		if(StringUtils.isEmpty(entity.getUsername()))
			throw new IllegalArgumentException("用户名不能为空");
		if(StringUtils.isEmpty(entity.getPassword()))
			throw new IllegalArgumentException("密码不能为空");
		if(roleIds==null || roleIds.length==0)
			throw new ServiceException("至少要为用户分配一个角色");
		
		String source=entity.getPassword();
		String salt=UUID.randomUUID().toString();//产生一个随机字符串
		
		//Spring> MD5加密：摘要加密算法，加密不可逆（解密），相同的
		
		//DigestUtils.md5DigestAsHex((source+salt).getBytes());//不方便加密多次
		SimpleHash sh = new SimpleHash(
				"MD5", //algorithmName 算法名称
				source, //source 原来的密码
				salt, //salt 盐值
				1);	//hashIterations 加密次数	 

		entity.setSalt(salt);
		entity.setPassword(sh.toHex());

		int rows = sysUserDao.insertObject(entity);
		sysUserRoleDao.insertObjects(entity.getId(), roleIds);
	//	log.info("end="+System.currentTimeMillis());
		
		//throw new ServiceException("save Error！");
		return rows;
	}

	@RequiredLog(operation="用户更新")
	@Override
	public int updateObject(SysUser entity, Integer[] roleIds) {
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername()))
			throw new IllegalArgumentException("用户名不能为空");
		if(roleIds==null||roleIds.length==0)
			throw new IllegalArgumentException("必须为其指定角色");
		int rows = sysUserDao.updateObject(entity);
		sysUserRoleDao.deleteObjectsByUserId(entity.getId());
		sysUserRoleDao.insertObjects(entity.getId(), roleIds);
		return rows;
	}

	@Override
	public int updatePassword(String password, String newPassword, String cfgPassword) {
		if(StringUtils.isEmpty(newPassword))
			throw new IllegalArgumentException("新密码不能为空");
		if(StringUtils.isEmpty(cfgPassword))
			throw new IllegalArgumentException("确认密码不能为空");
		if(!newPassword.equals(cfgPassword))
			throw new IllegalArgumentException("两次输入的密码不相等");
		if(StringUtils.isEmpty(password))
			throw new IllegalArgumentException("原密码不能为空");

		//获取登录用户
		SysUser user=(SysUser)SecurityUtils.getSubject().getPrincipal();
		SimpleHash sh=new SimpleHash("MD5",password,user.getSalt(),1);
		if(!user.getPassword().equals(sh.toHex()))
			throw new IllegalArgumentException("原密码不正确");
		String salt=UUID.randomUUID().toString();
		sh=new SimpleHash("MD5", newPassword, salt, 1);
		int rows=sysUserDao.updatePassword(
				sh.toHex(), salt, user.getId());

		if(rows==0)
			throw new ServiceException("修改失败");

		return rows;
	}

	@Override
	public Map<String, Object> findObjectById(Integer userId) {
		if(userId==null||userId<=0)
			throw new ServiceException(
					"参数数据不合法,userId="+userId);
		SysUserDeptVo user=
				sysUserDao.findObjectById(userId);
		if(user==null)
			throw new ServiceException("此用户已经不存在");
		List<Integer> roleIds=
				sysUserRoleDao.findRoleIdsByUserId(userId);
		Map<String,Object> map=new HashMap<>();
		map.put("user", user);
		map.put("roleIds", roleIds);
		return map;
	}

}
