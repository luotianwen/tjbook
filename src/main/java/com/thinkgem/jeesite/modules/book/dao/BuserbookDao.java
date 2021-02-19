/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.book.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.book.entity.Buserbook;

import java.util.List;

/**
 * 用户图书管理DAO接口
 * @author 用户图书管理
 * @version 2020-05-09
 */
@MyBatisDao
public interface BuserbookDao extends CrudDao<Buserbook> {

    List<Buserbook> findGroupByList();
}