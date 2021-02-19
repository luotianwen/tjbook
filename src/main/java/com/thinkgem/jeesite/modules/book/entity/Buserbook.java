/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.book.entity;

import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户图书管理Entity
 * @author 用户图书管理
 * @version 2020-05-09
 */
public class Buserbook extends DataEntity<Buserbook> {
	
	private static final long serialVersionUID = 1L;
	private Buser u;		// 用户
	private Bbook b;		// 书
	private Date cratetime;		// 创建时间
	
	public Buserbook() {
		super();
	}

	public Buserbook(String id){
		super(id);
	}

	public Buser getU() {
		return u;
	}

	public void setU(Buser u) {
		this.u = u;
	}
	
	public Bbook getB() {
		return b;
	}

	public void setB(Bbook b) {
		this.b = b;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCratetime() {
		return cratetime;
	}

	public void setCratetime(Date cratetime) {
		this.cratetime = cratetime;
	}
	
}