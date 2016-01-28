package com.tomorrow.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tomorrow.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {
        "classpath:spring/spring-mybatis.xml",
        "classpath:spring/spring-servlet.xml",
})
public class ServiceCacheTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testGetList(){
		System.out.println(userService.getUserByName("laien"));
	}
	
}
