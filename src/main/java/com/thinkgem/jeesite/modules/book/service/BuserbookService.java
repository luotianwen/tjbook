/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.book.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.book.entity.Buserbook;
import com.thinkgem.jeesite.modules.book.dao.BuserbookDao;

/**
 * 用户图书管理Service
 * @author 用户图书管理
 * @version 2020-05-09
 */
@Service
@Transactional(readOnly = true)
public class BuserbookService extends CrudService<BuserbookDao, Buserbook> {

	public Buserbook get(String id) {
		return super.get(id);
	}
	
	public List<Buserbook> findList(Buserbook buserbook) {
		return super.findList(buserbook);
	}
	
	public Page<Buserbook> findPage(Page<Buserbook> page, Buserbook buserbook) {
		return super.findPage(page, buserbook);
	}
	
	@Transactional(readOnly = false)
	public void save(Buserbook buserbook) {
		super.save(buserbook);
	}
	
	@Transactional(readOnly = false)
	public void delete(Buserbook buserbook) {
		super.delete(buserbook);
	}

    public List<Buserbook> findGroupByList() {
		return dao.findGroupByList();
    }
}