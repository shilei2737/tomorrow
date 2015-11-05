/*
 * Copyright (C), 2014-2015, 杭州小卡科技有限公司
 * FileName: UserController.java
 * Author:   Administrator
 * Date:     2015年11月5日 上午9:46:56
 * Description: 
 */
package com.tomorrow.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tomorrow.entity.User;
import com.tomorrow.redis.service.RedisService;
import com.tomorrow.service.UserService;

/**
 * 〈一句话是什么〉<br> 
 * 〈详细描述做什么〉
 *
 * @author Administrator
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
@RequestMapping(value="/user")
public class UserController {
	
	private static final String redisKeyName = "allUserJson";
	private static long value = 1;
	private Gson gson = new Gson();
	
	@Resource(name = "redisService")
	RedisService redisService;

	@Resource
	UserService userService;

	@RequestMapping(value = "/getList")
	@ResponseBody
	public List<User> getList() {
		List<User> userList;
		String redisUserValue = redisService.get(redisKeyName);
		if(null == redisUserValue || redisUserValue.isEmpty()){
			System.out.println("get form database");
			userList = userService.getAllUser();
			String allUserJson = gson.toJson(userList);
			System.out.println(allUserJson);
			redisService.set(redisKeyName, allUserJson);
			return userList;
		}else{
			System.out.println("get form redis");
			userList = gson.fromJson(redisUserValue,new TypeToken<List<User>>(){}.getType());
			System.out.println(userList);
			return userList;
		}
	}
	
	@RequestMapping(value = "/addList")
	public String addList(){
		List<User> list = new ArrayList<>(10002);
		Random random = new Random();
		for (long index = 0; index < 10000 ; index ++) {
			list.add(new User((random.nextInt(20) + 20)+"", random.nextInt(20) + 20, "address"));
		}
		userService.addUsers(list);
		return "success";
	}
}
