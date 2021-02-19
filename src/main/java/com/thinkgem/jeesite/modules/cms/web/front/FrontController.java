/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.web.front;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.thinkgem.jeesite.modules.book.entity.Bbook;
import com.thinkgem.jeesite.modules.book.entity.Buser;
import com.thinkgem.jeesite.modules.book.entity.Buserbook;
import com.thinkgem.jeesite.modules.book.service.BbookService;
import com.thinkgem.jeesite.modules.book.service.BuserService;
import com.thinkgem.jeesite.modules.book.service.BuserbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.servlet.ValidateCodeServlet;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.entity.Article;
import com.thinkgem.jeesite.modules.cms.entity.Category;
import com.thinkgem.jeesite.modules.cms.entity.Comment;
import com.thinkgem.jeesite.modules.cms.entity.Link;
import com.thinkgem.jeesite.modules.cms.entity.Site;
import com.thinkgem.jeesite.modules.cms.service.ArticleDataService;
import com.thinkgem.jeesite.modules.cms.service.ArticleService;
import com.thinkgem.jeesite.modules.cms.service.CategoryService;
import com.thinkgem.jeesite.modules.cms.service.CommentService;
import com.thinkgem.jeesite.modules.cms.service.LinkService;
import com.thinkgem.jeesite.modules.cms.service.SiteService;
import com.thinkgem.jeesite.modules.cms.utils.CmsUtils;

/**
 * 图书管理Controller
 * @author 图书管理
 * @version 2020-05-09
 */
@Controller
@RequestMapping(value = "${frontPath}")
public class FrontController extends BaseController {


    /**
     * 网站首页
     */
    @RequestMapping
    public String index(HttpServletRequest request, Model model) {
        Site site = CmsUtils.getSite(Site.defaultSiteId());
        model.addAttribute("site", site);
        model.addAttribute("isIndex", true);
        model.addAttribute("books", gettjbooks(request));

        return "modules/cms/front/themes/" + site.getTheme() + "/frontIndex";
    }

    @Autowired
    private BbookService bbookService;
    @Autowired
    private BuserbookService buserbookService;

    private List<Bbook> gettjbooks(HttpServletRequest request) {
        List<Bbook> bs =   Lists.newArrayList();
        Buser b = (Buser) request.getSession().getAttribute("buser");
        if(b==null){
            bs = bbookService.findList(new Bbook());
            return bs;
        }
        //推荐
        UserSet userSet = new UserSet();

        List<Buserbook> uls = buserbookService.findGroupByList();
        for (Buserbook u : uls
        ) {
            UserSet.User u1 = userSet.put(u.getU().getId());


            List<Buserbook> gls = buserbookService.findList(u);
            for (Buserbook g : gls
            ) {
                u1.set(g.getB().getId(), g.getB().getScore());
            }
            u1.create();
        }
        Recommend recommend = new Recommend();
        List<UserSet.Set> recommendations2 = recommend.recommend(b.getId(), userSet);
        List<UserSet.Set> recommendations = recommendations2.subList(0, recommendations2.size() > 15 ? 15 : recommendations2.size());
        //没有推荐
        if (recommendations.size() == 0) {
            Bbook b2=   new Bbook();
            b2.setId(b.getId());
               //过滤已经读过的
                bs = bbookService.findremList(b2);
                if(bs.size()==0){
                    //查询所有的
                    bs = bbookService.findList(new Bbook());
                }
        } else {
            for (UserSet.Set set : recommendations) {
                bs.add(bbookService.get(set.username));
            }

        }

        return bs;
    }

    @RequestMapping(value = "loginout")
    public String loginout(HttpServletRequest request, Buser buser, Model model) {
        Site site = CmsUtils.getSite(Site.defaultSiteId());
        model.addAttribute("site", site);
        request.getSession().removeAttribute("buser");
        return "redirect:" + Global.getFrontPath();
    }

    @RequestMapping(value = "login")
    public String login(HttpServletRequest request, Buser buser, Model model) {
        Site site = CmsUtils.getSite(Site.defaultSiteId());
        model.addAttribute("site", site);
        List<Buser> bs = buserService.findList(buser);
        if (null != bs && bs.size() > 0) {
            request.getSession().setAttribute("buser", bs.get(0));
        } else {
            addMessage(model, "手机号或者密码错误");
            return tologin(buser, model);
        }
        return "redirect:" + Global.getFrontPath();
    }

    @RequestMapping(value = "tologin")
    public String tologin(Buser buser, Model model) {
        Site site = CmsUtils.getSite(Site.defaultSiteId());
        model.addAttribute("site", site);
        return "modules/cms/front/themes/" + site.getTheme() + "/fronttologin";
    }

    @RequestMapping(value = "toregister")
    public String toregister(Buser buser, Model model) {
        Site site = CmsUtils.getSite(Site.defaultSiteId());
        model.addAttribute("site", site);
        return "modules/cms/front/themes/" + site.getTheme() + "/fronttoregister";
    }

    @Autowired
    private BuserService buserService;

    @RequestMapping(value = "register")
    public String register(Buser buser, Model model) {
        Site site = CmsUtils.getSite(Site.defaultSiteId());
        buser.setStatus("1");
        model.addAttribute("site", site);
        if (!beanValidator(model, buser)) {
            return toregister(buser, model);
        }
        try {
            buserService.save(buser);
        } catch (Exception e) {
            return "modules/cms/front/themes/" + site.getTheme() + "/fronttoregister";
        }
        return "redirect:" + Global.getFrontPath() + "/tologin";
    }

    /**
     * 网站首页
     */
    @RequestMapping(value = "index-{siteId}${urlSuffix}")
    public String index(@PathVariable String siteId, Model model) {
        return "redirect:" + Global.getFrontPath();


    }

    /**
     * 显示内容
     */
    @RequestMapping(value = "book/{contentId}")
    public String view(HttpServletRequest request,  @PathVariable String contentId, Model model) {
        Site site = CmsUtils.getSite(Site.defaultSiteId());
        model.addAttribute("site", site);

            // 获取文章内容
            Bbook book = bbookService.get(contentId);
            if (book == null || !Article.DEL_FLAG_NORMAL.equals(book.getDelFlag())) {
                return "error/404";
            }
            // 阅读次数+1
            book.setLll(book.getLll()+1);
            bbookService.save(book);
            // 获取推荐文章列表
            Bbook b2=new Bbook();
            b2.setCid(book.getCid());
            b2.setId(book.getId());
            List<Bbook> tjs = bbookService.findtjList(b2);
        Buser b = (Buser) request.getSession().getAttribute("buser");
            if(b!=null){
                Buserbook buserbook=new Buserbook();
                buserbook.setB(book);
                buserbook.setU(b);
                buserbook.setCratetime(new Date());
                try {
                    buserbookService.save(buserbook);
                }catch (Exception e){

                }
            }

            model.addAttribute("relationList", tjs);
             model.addAttribute("tjs", gettjbooks(request));
            // 将数据传递到视图
            model.addAttribute("book", book);
            return "modules/cms/front/themes/" + site.getTheme() + "/frontViewArticle" ;
    }


}
