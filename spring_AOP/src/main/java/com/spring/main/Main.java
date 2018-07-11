package com.spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.spring.dto.Article;
import com.spring.service.ArticleService;
import com.spring.service.ArticleServiceImpl;

public class Main {

	public static void main(String[] args) {
		
		ApplicationContext ctx=
				new GenericXmlApplicationContext
				("classpath:application-context.xml");
		/*
		행동 behavior=(행동)ctx.getBean("행동");
		
		behavior.sleeping();
		behavior.eatting();
		behavior.shower();*/
		
		ArticleService service=	(ArticleService)ctx.getBean("articleService");
		Article article=new Article();
		article.setContent("내용입니다.");
		article.setTitle("제목입니다.");
		article.setWriterName("작성자");
		
		service.write(article);
		service.getArticle(1);

	}

}





