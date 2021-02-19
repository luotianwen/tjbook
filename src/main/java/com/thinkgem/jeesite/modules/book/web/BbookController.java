/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.book.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.book.entity.Bclass;
import com.thinkgem.jeesite.modules.book.service.BclassService;
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
import com.thinkgem.jeesite.modules.book.entity.Bbook;
import com.thinkgem.jeesite.modules.book.service.BbookService;

/**
 * 图书管理Controller
 * @author 图书管理
 * @version 2020-05-09
 */
@Controller
@RequestMapping(value = "${adminPath}/book/bbook")
public class BbookController extends BaseController {

	@Autowired
	private BbookService bbookService;
	
	@ModelAttribute
	public Bbook get(@RequestParam(required=false) String id) {
		Bbook entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bbookService.get(id);
		}
		if (entity == null){
			entity = new Bbook();
		}
		return entity;
	}
	
	@RequiresPermissions("book:bbook:view")
	@RequestMapping(value = {"list", ""})
	public String list(Bbook bbook, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Bbook> page = bbookService.findPage(new Page<Bbook>(request, response), bbook); 
		model.addAttribute("page", page);
		return "modules/book/bbookList";
	}
	@Autowired
	private BclassService bclassService;
	@RequiresPermissions("book:bbook:view")
	@RequestMapping(value = "form")
	public String form(Bbook bbook, Model model) {
		model.addAttribute("cs", bclassService.findList(new Bclass()));
		model.addAttribute("bbook", bbook);
		return "modules/book/bbookForm";
	}

	@RequiresPermissions("book:bbook:edit")
	@RequestMapping(value = "save")
	public String save(Bbook bbook, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bbook)){
			return form(bbook, model);
		}
		bbook.setCname(bclassService.get(bbook.getCid()).getName());
		bbookService.save(bbook);
		addMessage(redirectAttributes, "保存图书管理成功");
		return "redirect:"+Global.getAdminPath()+"/book/bbook/?repage";
	}
	
	@RequiresPermissions("book:bbook:edit")
	@RequestMapping(value = "delete")
	public String delete(Bbook bbook, RedirectAttributes redirectAttributes) {
		bbookService.delete(bbook);
		addMessage(redirectAttributes, "删除图书管理成功");
		return "redirect:"+Global.getAdminPath()+"/book/bbook/?repage";
	}

}