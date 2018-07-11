package com.spring.dao;

import com.spring.dto.Article;

public interface ArticleDAO {
	
	void insertArticle(Article article);
	Article selectById(int id);
}
