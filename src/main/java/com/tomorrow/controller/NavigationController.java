/*
 * Copyright (C), 2014-2016, 杭州小卡科技有限公司
 * FileName: NavigationController.java
 * Author:   laien
 * Date:     2016年2月29日 上午11:07:22
 * Description: 
 */
package com.tomorrow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈一句话是什么〉<br> 
 * 〈详细描述做什么〉
 *
 * @author laien
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
public class NavigationController {
	
	@RequestMapping("/homePage")
	public String homePage(){
		return "jsp/main";
	}
}
