package com.cy.pj.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 此Controller主要负责响应一些页面
 */
@RequestMapping("/")
@Controller
public class PageController {
	
	@RequestMapping("doLoginUI")
	public String doLoginUI(){
			return "login";
	}
	
	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		return "starter";
	}
	
	/**
	 * 返回分页页面
	 * @return
	 */
	@RequestMapping("doPageUI")
	public String doPageUI() {
		System.out.println("PageController.doPageUI()");
		return "common/page";
	}
	
	/**
	 * 优化页面跳转的方法
	 * @PathVariable 描述的方法参数，表示他的值从url路径中获取（和参数名相同的URL变量）
	 * 
	 * @param moduleUI
	 * @return
	 */
	//rest 风格的一种url定义，语法{url}	
	@RequestMapping("{module}/{moduleUI}")
	public String doModuleUI(@PathVariable String moduleUI) {
		return "sys/"+moduleUI;
	}
	
	@RequestMapping("{xz/{moduleUI}")
	public String doModuleUI2(@PathVariable String moduleUI) {
		return "xz/"+moduleUI;
	}
	
}




