package com.spring.service;

import com.spring.dto.Article;

public interface ArticleService {
	
	void write(Article article);
	Article getArticle(int id);
}
