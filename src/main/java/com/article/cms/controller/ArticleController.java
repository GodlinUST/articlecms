package com.article.cms.controller;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.article.cms.model.Article;
import com.article.cms.model.HtmlArticle;
import com.article.cms.service.ArticleService;

@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(value = {"/welcome"} , method = RequestMethod.GET)
	public String sayWelcome(ModelMap map){
		map.addAttribute("welcomemsg", "Welcome to Article CMS");
		return "index";
	}
	
	@RequestMapping (value = {"/listarticle"}, method = RequestMethod.GET)
	public ModelAndView findAllArticle(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if(null != session.getAttribute("user")){
		mv.addObject("userID", session.getAttribute("user"));	
		mv.addObject("listArticle", articleService.getAllArticle());
		mv.setViewName("article");
		}
		else
		{response.sendRedirect("/articlecms/welcome");}	
		return mv;
	}
	
	@RequestMapping (value = {"/editarticle"}, method = RequestMethod.GET)
	public ModelAndView editArticle(@RequestParam String articleid, ModelAndView mv) throws Exception {
		int id = Integer.parseInt(articleid);
		Article article = articleService.findArticleById(id);
		HtmlArticle hArticle = null;
		hArticle = parseXml(article.getBody(), hArticle);
		mv.addObject("listArticle", articleService.getAllArticle());
		mv.addObject("TitleArticle", article.getTitle());
		mv.addObject("TeaserArticle", article.getTeaser());
		mv.addObject("ResourcesArticle", hArticle.getResources());
		mv.addObject("HeaderArticle", hArticle.getHeader());
		mv.addObject("BodyArticle", hArticle.getBody());
		mv.addObject("FooterArticle", hArticle.getFooter());
		mv.setViewName("article");
		return mv;
	}
	
	@RequestMapping (value = {"/articledelete"}, method = RequestMethod.GET)
	public void deleteArticle(@RequestParam String articleid, HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(articleid);
		articleService.deleteArticle(id);
		try {
			response.sendRedirect("/articlecms/listarticle");
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
	
	@RequestMapping (value = {"/addarticle"}, method = RequestMethod.POST)
	public void addArticle(HttpServletRequest request, HttpServletResponse response) {
		StringWriter sw = new StringWriter();
		Article article = new Article();
		article.setTitle(request.getParameter("title"));
		article.setTeaser(request.getParameter("teaser"));
		HtmlArticle hArticle = new HtmlArticle();
		hArticle.setResources(null != request.getParameter("Resources")?request.getParameter("Resources"):"");
		hArticle.setHeader(null != request.getParameter("Header")?request.getParameter("Header"):"");
		hArticle.setBody(null != request.getParameter("body")?request.getParameter("body"):"");
		hArticle.setFooter(null != request.getParameter("footer")?request.getParameter("footer"):"");
		JAXBContext jaxbContext;Marshaller jaxbMarshaller;
		try{
		jaxbContext = JAXBContext.newInstance(HtmlArticle.class);
		jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(hArticle, sw);
//		jaxbMarshaller.marshal(hArticle, System.out);
		} catch(Exception e){}
			
		article.setBody(sw.toString());
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
		HtmlArticle hArticle = null;
		try {
			hArticle = parseXml(details,hArticle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.addAttribute("articleresources", hArticle.getResources());
		map.addAttribute("articleheader", hArticle.getHeader());		
		map.addAttribute("articlebody", hArticle.getBody());
		map.addAttribute("articlefooter", hArticle.getFooter());
		return "articledetails";
	}
	
	public HtmlArticle parseXml(String xmlResponse, HtmlArticle htmlArticle) throws Exception
	{

	 StringReader stringReader = new StringReader(xmlResponse);
	            JAXBContext jaxbContext = JAXBContext.newInstance(HtmlArticle.class);
	            XMLInputFactory xif = XMLInputFactory.newInstance();
	            XMLStreamReader xsr = xif.createXMLStreamReader(stringReader);
	            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	            return (HtmlArticle) unmarshaller.unmarshal(xsr);
	}
	
	
	@RequestMapping (value = {"/login"}, method = RequestMethod.POST)
	public void logincms(@RequestParam String userid,@RequestParam String pwd, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String user = null;
		HttpSession session = request.getSession(true);
		if(userid.equals("article") && pwd.equals("article"))
		{
		user = userid;
		session.setAttribute("user", user);
		response.sendRedirect("/articlecms/listarticle");
		}
		else
		{
		user = null;response.sendRedirect("/articlecms/welcome");
		}	
		}
		
	@RequestMapping (value = {"/logout"}, method = RequestMethod.GET)
	public void logoutcms(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if(null != session.getAttribute("user"))
		{
		session.invalidate();
		response.sendRedirect("/articlecms/welcome");
		}
		
		}
}
