/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.book.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 图书管理Entity
 * @author 图书管理
 * @version 2020-05-09
 */
public class Bbook extends DataEntity<Bbook> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 书名
	private String cid;		// 分类id
	private String cname;		// 分类名称
	private String status;		// 状态
	private int lll;		// 浏览量
	private int score;		// 浏览量

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	private String num;		// 序号
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	
	public Bbook() {
		super();
	}

	public Bbook(String id){
		super(id);
	}

	@Length(min=0, max=200, message="书名长度必须介于 0 和 200 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=32, message="分类id长度必须介于 0 和 32 之间")
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
	
	@Length(min=0, max=255, message="分类名称长度必须介于 0 和 255 之间")
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	
	@Length(min=0, max=255, message="状态长度必须介于 0 和 255 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getLll() {
		return lll;
	}

	public void setLll(int lll) {
		this.lll = lll;
	}
	
	@Length(min=1, max=11, message="序号长度必须介于 1 和 11 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
		
}