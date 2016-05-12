package com.article.cms.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.article.cms.model.Article;
import com.article.cms.service.ArticleService;

@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(value = {"/hallo"} , method = RequestMethod.GET)
	public String sayWelcome(ModelMap map){
		map.addAttribute("welcomemsg", "Welcome to Article");
		return "index";
	}
	
	@RequestMapping (value = {"/listarticle"}, method = RequestMethod.GET)
	public ModelAndView findAllArticle(ModelAndView mv) {
		mv.addObject("listArticle", articleService.getAllArticle());
		mv.setViewName("article");
		return mv;
	}
	
	@RequestMapping (value = {"/addarticle"}, method = RequestMethod.POST)
	public void addArticle(HttpServletRequest request, HttpServletResponse response) {
		Article article = new Article();
		article.setTitle(request.getParameter("title"));
		article.setTeaser(request.getParameter("teaser"));
		article.setBody(request.getParameter("body"));
		articleService.addArticle(article);
	try {
		response.sendRedirect("/articlecms/listarticle");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
	
	@RequestMapping (value = {"/articledetails"}, method = RequestMethod.GET)
	public String getArticleDetails(@RequestParam String articleid, ModelMap map){
		int id = Integer.parseInt(articleid);
		String details = articleService.findArticleById(id).getBody();
		map.addAttribute("articledetails", details);
		return "articledetails";
	}
	
	
}
