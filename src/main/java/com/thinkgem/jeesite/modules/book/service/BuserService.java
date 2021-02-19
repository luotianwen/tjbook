/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.book.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.book.entity.Buser;
import com.thinkgem.jeesite.modules.book.dao.BuserDao;

/**
 * 用户管理Service
 * @author 用户管理
 * @version 2020-05-09
 */
@Service
@Transactional(readOnly = true)
public class BuserService extends CrudService<BuserDao, Buser> {

	public Buser get(String id) {
		return super.get(id);
	}
	
	public List<Buser> findList(Buser buser) {
		return super.findList(buser);
	}
	
	public Page<Buser> findPage(Page<Buser> page, Buser buser) {
		return super.findPage(page, buser);
	}
	
	@Transactional(readOnly = false)
	public void save(Buser buser) {
		super.save(buser);
	}
	
	@Transactional(readOnly = false)
	public void delete(Buser buser) {
		super.delete(buser);
	}
	
}