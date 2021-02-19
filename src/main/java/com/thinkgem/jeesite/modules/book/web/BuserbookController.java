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
import com.thinkgem.jeesite.modules.book.entity.Buserbook;
import com.thinkgem.jeesite.modules.book.service.BuserbookService;

/**
 * 用户图书管理Controller
 * @author 用户图书管理
 * @version 2020-05-09
 */
@Controller
@RequestMapping(value = "${adminPath}/book/buserbook")
public class BuserbookController extends BaseController {

	@Autowired
	private BuserbookService buserbookService;
	
	@ModelAttribute
	public Buserbook get(@RequestParam(required=false) String id) {
		Buserbook entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = buserbookService.get(id);
		}
		if (entity == null){
			entity = new Buserbook();
		}
		return entity;
	}
	
	@RequiresPermissions("book:buserbook:view")
	@RequestMapping(value = {"list", ""})
	public String list(Buserbook buserbook, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Buserbook> page = buserbookService.findPage(new Page<Buserbook>(request, response), buserbook); 
		model.addAttribute("page", page);
		return "modules/book/buserbookList";
	}

	@RequiresPermissions("book:buserbook:view")
	@RequestMapping(value = "form")
	public String form(Buserbook buserbook, Model model) {
		model.addAttribute("buserbook", buserbook);
		return "modules/book/buserbookForm";
	}

	@RequiresPermissions("book:buserbook:edit")
	@RequestMapping(value = "save")
	public String save(Buserbook buserbook, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, buserbook)){
			return form(buserbook, model);
		}
		buserbookService.save(buserbook);
		addMessage(redirectAttributes, "保存用户图书管理成功");
		return "redirect:"+Global.getAdminPath()+"/book/buserbook/?repage";
	}
	
	@RequiresPermissions("book:buserbook:edit")
	@RequestMapping(value = "delete")
	public String delete(Buserbook buserbook, RedirectAttributes redirectAttributes) {
		buserbookService.delete(buserbook);
		addMessage(redirectAttributes, "删除用户图书管理成功");
		return "redirect:"+Global.getAdminPath()+"/book/buserbook/?repage";
	}

}