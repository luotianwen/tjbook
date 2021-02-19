/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.book.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 分类管理Entity
 * @author 分类管理
 * @version 2020-05-09
 */
public class Bclass extends DataEntity<Bclass> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	
	public Bclass() {
		super();
	}

	public Bclass(String id){
		super(id);
	}

	@Length(min=0, max=255, message="名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}