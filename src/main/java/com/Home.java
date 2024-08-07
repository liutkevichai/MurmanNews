package com;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Article;
import models.dbFunctions;

/**
 * Класс Home наследует от абстрактного класса HttpServlet, переопределяя его метод doGet.<br>
 * При обращении к корневой странице сайта, сервлет Home передает запрос на страницу /homepage.jsp.
 */
@WebServlet("/")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	* Метод doGet сервлета Home.<br>
	* Передает запрос на страницу homepage.jsp.<br>
	* <b>Устанавливает атрибуты запроса: "URI".</b><br>
	* @param request - HTTP запрос
	* @param response - HTTP ответ
	*/
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Метод doGet сервлета Home"); //DEBUG 
		
		dbFunctions DB = new dbFunctions();
		Connection conn = DB.connectToDB();
		
		ArrayList<Article> mainNews = DB.getArticlesReversedLimited(conn, 6);
		
		request.setAttribute("mainNews", mainNews);
    	request.setAttribute("URI", request.getRequestURI());
    	
		request.getRequestDispatcher("/homepage.jsp").forward(request, response);
	}

}
