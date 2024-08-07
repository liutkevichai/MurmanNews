package com;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.*;
import service.PathProvider;

/**
 * Класс Feed наследует от абстрактного класса HttpServlet, переопределяя его методы doGet, doPost и doPut.<br>
 * Сервлет Feed отвечает за отображение ленты и статей, их создание и изменение.
 */
@WebServlet(urlPatterns= {"/feed", "/feed/*", "/post"})
public class Feed extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	* Метод doGet сервлета Feed.<br>
	* Если Get-запрос пришел по адресу /feed, передает запрос на страницу feed.jsp.<br>
	* Если Get-запрос пришел от пользователя типа "автор" по адресу /post, передает запрос на страницу article_posting.jsp.<br>
	* Если Get-запрос пришел по адресу /feed/{id}, передает запрос методу doGet сервлета Comments.<br>
	* В остальных случаях перенаправляет пользователя на главную страницу.<br>
	* <b>Устанавливает атрибуты запроса: "reversedArticleIds", "allArticles", "articleId", "article", "URI".</b><br>
	* <b>Получает атрибут сессии "user".</b>
	* @param request - HTTP запрос
	* @param response - HTTP ответ
	*/
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Метод doGet сервлета Feed"); //DEBUG 
		
		User user = (User) request.getSession().getAttribute("user");
		
		dbFunctions DB = new dbFunctions();
		Connection conn = DB.connectToDB();
		
		if ( request.getRequestURI().equals( request.getContextPath() + "/feed") ) {
			
			ArrayList<Article> reversedArticles = DB.getArticlesReversed(conn);

	    	request.setAttribute("reversedArticles", reversedArticles);
	    	request.setAttribute("URI", request.getRequestURI());
	    	
			request.getRequestDispatcher("/feed.jsp").forward(request, response);
			
		} else  if ( request.getRequestURI().equals( request.getContextPath() + "/post") && 
				user != null && user.getRole().equals("автор")) {
			
				request.getRequestDispatcher("/article_posting.jsp").forward(request, response);
			
		} else  if ( Pattern.matches(request.getContextPath() + "/feed/\\d+$", request.getRequestURI()) ) {
			
			if ( request.getParameter("delete") != null ) {
				doDelete(request, response);
				
			} else {
				try {
					Integer articleId = Integer.valueOf(PathProvider.getEndpoint(request, -1));
					
					request.setAttribute("article", DB.getArticle(conn, articleId));
					request.setAttribute("URI", request.getRequestURI());
					
					request.getRequestDispatcher("/comments").forward(request, response);
				
				} catch ( java.lang.NullPointerException ex ) { response.sendRedirect(request.getContextPath()  + "/notfound.jsp"); }
			}
			
		} else { response.sendRedirect(request.getContextPath() + "/notfound.jsp"); }
		
	}
	
	/**
	* Метод doPost сервлета Feed. Получает из запроса параметры формы постинга и создает новую статью, 
	* затем перенаправляет пользователя на метод doGet сервлета Feed.<br>
	* Если Post-запрос пришел по адресу /feed/{id}, вызывает метод doPut().<br>
	* <b>Получает параметры запроса: "author", "title", "contents", "image".</b><br>
	* <b>Получает атрибут сессии "user".</b>
	* @param request - HTTP запрос
	* @param response - HTTP ответ
	*/
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Метод doPost сервлета Feed"); //DEBUG 
		
		User user = (User) request.getSession().getAttribute("user");
		
		if ( user.getRole().equals("автор") ) {
			
			if ( request.getRequestURI().equals( request.getContextPath() + "/post") ) {
				
				dbFunctions DB = new dbFunctions();
				Connection conn = DB.connectToDB();
				
				String author = request.getParameter("author");
				String title = request.getParameter("title");
				String contents = request.getParameter("contents");
				String image = request.getParameter("image");
				
				DB.createArticle(conn, author, title, contents, image);
				
				response.sendRedirect(request.getContextPath() + "/feed");
				
			} else if ( Pattern.matches(request.getContextPath() + "/feed/\\d+$", request.getRequestURI()) ) {
				
				doPut(request, response);
				
			} else { response.sendRedirect(request.getContextPath()); }
		}
		
	}
	
	/**
	* Метод doPut сервлета Feed. Получает из запроса параметры формы редактирования статьи и вносит изменения, 
	* затем перенаправляет пользователя на страницу отредактированной статьи.<br>
	* <b>Получает параметры запроса: "articleId", "author", "title", "contents", "image".</b><br>
	* <b>Получает атрибут сессии "user".</b>
	* @param request - HTTP запрос
	* @param response - HTTP ответ
	*/
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Метод doPut сервлета Feed"); //DEBUG
		
		User user = (User) request.getSession().getAttribute("user");
		
		Integer articleId = Integer.valueOf(request.getParameter("articleId"));
		String author = request.getParameter("author");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String image = request.getParameter("image");

		dbFunctions DB = new dbFunctions();
		Connection conn = DB.connectToDB();
		
		if ( DB.getArticle(conn, articleId) != null && user.getUsername().equalsIgnoreCase(author) ) {
			DB.updateArticle(conn, articleId, title, contents, image);
		}
		
		response.sendRedirect(request.getContextPath() + "/feed/" + articleId);
	}
	
	/**
	* Метод doDelete сервлета Feed. Если пользователь является автором статьи, удаляет статью.<br>
	* Перенаправляет пользователя на страницу ленты новостей.<br>
	* <b>Получает параметр запроса "delete".</b><br>
	* <b>Получает атрибут сессии "user".</b>
	* @param request - HTTP запрос
	* @param response - HTTP ответ
	*/
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Метод doDelete сервлета Feed"); //DEBUG
		
		User user = (User) request.getSession().getAttribute("user");
		
		Integer id = Integer.valueOf(request.getParameter("delete"));
		
		dbFunctions DB = new dbFunctions();
		Connection conn = DB.connectToDB();
		
		Article article = DB.getArticle(conn, id);
		String articleAuthor = article.getAuthor();
		
		if ( user.getUsername().equalsIgnoreCase(articleAuthor) ) {
			DB.deleteArticle(conn, id);
		}
		
		response.sendRedirect(request.getContextPath() + "/feed");
	}

}
