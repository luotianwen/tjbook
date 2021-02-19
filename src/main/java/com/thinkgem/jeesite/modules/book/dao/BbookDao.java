/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.book.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.book.entity.Bbook;

import java.util.List;

/**
 * 图书管理DAO接口
 * @author 图书管理
 * @version 2020-05-09
 */
@MyBatisDao
public interface BbookDao extends CrudDao<Bbook> {

    List<Bbook> findtjList(Bbook b2);

    List<Bbook> findremList(Bbook b2);
}