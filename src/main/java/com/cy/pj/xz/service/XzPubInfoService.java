package com.cy.pj.xz.service;

import java.util.List;

import com.cy.pj.xz.entity.XzPubInfo;

public interface XzPubInfoService {
		List<XzPubInfo> findPubInfo();
		int insertPubInfo(XzPubInfo entity);
}
