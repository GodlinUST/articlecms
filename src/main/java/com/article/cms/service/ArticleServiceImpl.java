package com.article.cms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.article.cms.dao.ArticleDao;
import com.article.cms.model.Article;

@Repository(value = "articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;
	
	
	public List<Article> getAllArticle() {
		return articleDao.listArticles();
	}

	public Article findArticleById(int id) {
	return articleDao.getArticle(id);
	}

	public void addArticle(Article article) {
	articleDao.addArticle(article);

	}

	public void saveArticle(Article article) {
		// TODO Auto-generated method stub

	}

	public boolean deleteArticle(int id) {
		return articleDao.deleteArticle(id);
	}

}
