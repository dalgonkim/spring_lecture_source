package com.spring.service;

import com.spring.dao.ArticleDAO;
import com.spring.dto.Article;

public class ArticleServiceImpl implements ArticleService{

	private ArticleDAO articleDao;
	public void setArticleDao(ArticleDAO articleDao){
		this.articleDao=articleDao;
	}
	
	@Override
	public void write(Article article) {
		 System.out.println("ArticleServiceImpl.write() 호출됨"); 
		 articleDao.insertArticle(article);
		 System.out.println("ArticleServiceImpl.write() 종료됨");
	}

	@Override
	public Article getArticle(int id) {		
		System.out.println("ArticleService.getArticle()....");
		return articleDao.selectById(id);
	}

}
