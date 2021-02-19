/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.book.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.book.entity.Buser;
import com.thinkgem.jeesite.modules.book.service.BuserService;

/**
 * 用户管理Controller
 * @author 用户管理
 * @version 2020-05-09
 */
@Controller
@RequestMapping(value = "${adminPath}/book/buser")
public class BuserController extends BaseController {

	@Autowired
	private BuserService buserService;
	
	@ModelAttribute
	public Buser get(@RequestParam(required=false) String id) {
		Buser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = buserService.get(id);
		}
		if (entity == null){
			entity = new Buser();
		}
		return entity;
	}
	
	@RequiresPermissions("book:buser:view")
	@RequestMapping(value = {"list", ""})
	public String list(Buser buser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Buser> page = buserService.findPage(new Page<Buser>(request, response), buser); 
		model.addAttribute("page", page);
		return "modules/book/buserList";
	}

	@RequiresPermissions("book:buser:view")
	@RequestMapping(value = "form")
	public String form(Buser buser, Model model) {
		model.addAttribute("buser", buser);
		return "modules/book/buserForm";
	}

	@RequiresPermissions("book:buser:edit")
	@RequestMapping(value = "save")
	public String save(Buser buser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, buser)){
			return form(buser, model);
		}
		buserService.save(buser);
		addMessage(redirectAttributes, "保存用户管理成功");
		return "redirect:"+Global.getAdminPath()+"/book/buser/?repage";
	}
	
	@RequiresPermissions("book:buser:edit")
	@RequestMapping(value = "delete")
	public String delete(Buser buser, RedirectAttributes redirectAttributes) {
		buserService.delete(buser);
		addMessage(redirectAttributes, "删除用户管理成功");
		return "redirect:"+Global.getAdminPath()+"/book/buser/?repage";
	}

}