/*
 * Copyright (C), 2014-2015, 杭州小卡科技有限公司
 * FileName: User.java
 * Author:   Administrator
 * Date:     2015年9月23日 上午11:54:34
 * Description: 
 */
package com.tomorrow.entity;

/**
 * 〈一句话是什么〉<br> 
 * 〈详细描述做什么〉
 *
 * @author Administrator
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class User {
	private long id;
	private String name;
	private int age;
	private String address;
	/**
	 * 
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param id
	 * @param name
	 * @param age
	 * @param address
	 */
	public User( String name, int age, String address) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", address=" + address + "]";
	}
	
}
