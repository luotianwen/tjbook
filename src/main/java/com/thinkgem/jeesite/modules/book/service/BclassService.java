/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.book.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.book.entity.Bclass;
import com.thinkgem.jeesite.modules.book.dao.BclassDao;

/**
 * 分类管理Service
 * @author 分类管理
 * @version 2020-05-09
 */
@Service
@Transactional(readOnly = true)
public class BclassService extends CrudService<BclassDao, Bclass> {

	public Bclass get(String id) {
		return super.get(id);
	}
	
	public List<Bclass> findList(Bclass bclass) {
		return super.findList(bclass);
	}
	
	public Page<Bclass> findPage(Page<Bclass> page, Bclass bclass) {
		return super.findPage(page, bclass);
	}
	
	@Transactional(readOnly = false)
	public void save(Bclass bclass) {
		super.save(bclass);
	}
	
	@Transactional(readOnly = false)
	public void delete(Bclass bclass) {
		super.delete(bclass);
	}
	
}