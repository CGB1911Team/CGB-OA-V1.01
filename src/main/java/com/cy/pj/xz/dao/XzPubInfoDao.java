package com.cy.pj.xz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.xz.entity.XzPubInfo;

@Mapper
public interface XzPubInfoDao {
	/**
	 * 查询所有公告
	 * @return
	 */
	@Select("select * from pub_info")
	List<XzPubInfo> findPubInfo();
	/**
	 * 添加公告信息
	 * @param entity
	 * @return
	 */
	@Insert("insert into pub_info(id,subTitle,article,author,createdTime,modifiedTime)values"
			+ "(#{id},#{subTitle},#{article},#{author},now(),now())")
	int insertPubInfo(XzPubInfo entity);
}
