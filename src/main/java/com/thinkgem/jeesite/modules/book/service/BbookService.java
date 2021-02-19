/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.book.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.book.entity.Bbook;
import com.thinkgem.jeesite.modules.book.dao.BbookDao;

/**
 * 图书管理Service
 * @author 图书管理
 * @version 2020-05-09
 */
@Service
@Transactional(readOnly = true)
public class BbookService extends CrudService<BbookDao, Bbook> {

	public Bbook get(String id) {
		return super.get(id);
	}
	
	public List<Bbook> findList(Bbook bbook) {
		return super.findList(bbook);
	}
	
	public Page<Bbook> findPage(Page<Bbook> page, Bbook bbook) {
		return super.findPage(page, bbook);
	}
	
	@Transactional(readOnly = false)
	public void save(Bbook bbook) {
		super.save(bbook);
	}
	
	@Transactional(readOnly = false)
	public void delete(Bbook bbook) {
		super.delete(bbook);
	}

    public List<Bbook> findtjList(Bbook b2) {
		return  dao.findtjList(b2);
    }

	public List<Bbook> findremList(Bbook b2) {
		return  dao.findremList(b2);
	}
}