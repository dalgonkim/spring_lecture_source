package com.spring.dao;

import java.util.HashMap;
import java.util.Map;

import com.spring.dto.Article;

public class ArticleDAOImpl implements ArticleDAO{
	
	private int articleNo=0;
	private Map<Integer,Article> articleMap=
			new HashMap<Integer,Article>();
	
		
	@Override
	public void insertArticle(Article article) {
		System.out.println("ArticleDAOImpl.insertArticle() 호출됨");
		articleNo++;
		article.setId(articleNo);
		articleMap.put(articleNo, article);
		System.out.println("ArticleDAOImpl.insertArticle() 종료됨");
	}

	@Override
	public Article selectById(int id) {		
		return articleMap.get(id);
	}

}





