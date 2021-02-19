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
import com.thinkgem.jeesite.modules.book.entity.Bclass;
import com.thinkgem.jeesite.modules.book.service.BclassService;

/**
 * 分类管理Controller
 * @author 分类管理
 * @version 2020-05-09
 */
@Controller
@RequestMapping(value = "${adminPath}/book/bclass")
public class BclassController extends BaseController {

	@Autowired
	private BclassService bclassService;
	
	@ModelAttribute
	public Bclass get(@RequestParam(required=false) String id) {
		Bclass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bclassService.get(id);
		}
		if (entity == null){
			entity = new Bclass();
		}
		return entity;
	}
	
	@RequiresPermissions("book:bclass:view")
	@RequestMapping(value = {"list", ""})
	public String list(Bclass bclass, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Bclass> page = bclassService.findPage(new Page<Bclass>(request, response), bclass); 
		model.addAttribute("page", page);
		return "modules/book/bclassList";
	}

	@RequiresPermissions("book:bclass:view")
	@RequestMapping(value = "form")
	public String form(Bclass bclass, Model model) {
		model.addAttribute("bclass", bclass);
		return "modules/book/bclassForm";
	}

	@RequiresPermissions("book:bclass:edit")
	@RequestMapping(value = "save")
	public String save(Bclass bclass, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bclass)){
			return form(bclass, model);
		}
		bclassService.save(bclass);
		addMessage(redirectAttributes, "保存分类管理成功");
		return "redirect:"+Global.getAdminPath()+"/book/bclass/?repage";
	}
	
	@RequiresPermissions("book:bclass:edit")
	@RequestMapping(value = "delete")
	public String delete(Bclass bclass, RedirectAttributes redirectAttributes) {
		bclassService.delete(bclass);
		addMessage(redirectAttributes, "删除分类管理成功");
		return "redirect:"+Global.getAdminPath()+"/book/bclass/?repage";
	}

}