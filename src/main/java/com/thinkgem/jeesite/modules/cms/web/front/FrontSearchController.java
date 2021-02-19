/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.web.front;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.book.entity.Bbook;
import com.thinkgem.jeesite.modules.book.service.BbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.entity.Article;
import com.thinkgem.jeesite.modules.cms.entity.Guestbook;
import com.thinkgem.jeesite.modules.cms.entity.Site;
import com.thinkgem.jeesite.modules.cms.service.ArticleService;
import com.thinkgem.jeesite.modules.cms.service.GuestbookService;
import com.thinkgem.jeesite.modules.cms.utils.CmsUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${frontPath}/search")
public class FrontSearchController extends BaseController{

	@Autowired
	private BbookService bbookService;
	/**
	 * 全站搜索
	 */
	@RequestMapping(value = "")
	public String search( @RequestParam(required=false) String q,   HttpServletRequest request, HttpServletResponse response, Model model) {
		long start = System.currentTimeMillis();
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		Bbook bbook=new Bbook();
		bbook.setTitle(q);
		Page<Bbook> page = bbookService.findPage(new Page<Bbook>(request, response), bbook);
		model.addAttribute("page", page);
		page.setMessage("匹配结果，共耗时 " + (System.currentTimeMillis() - start) + "毫秒。") ;
		model.addAttribute("q", q);// 搜索关键字

		return "modules/cms/front/themes/"+site.getTheme()+"/frontSearch";
	}
	
}
