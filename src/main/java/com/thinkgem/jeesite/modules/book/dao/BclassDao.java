/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.book.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.book.entity.Bclass;

/**
 * 分类管理DAO接口
 * @author 分类管理
 * @version 2020-05-09
 */
@MyBatisDao
public interface BclassDao extends CrudDao<Bclass> {
	
}