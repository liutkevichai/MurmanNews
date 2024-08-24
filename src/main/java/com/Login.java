package com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.SQLException;

import models.dbFunctions;
import models.User;

/**
 * Класс Login наследует от абстрактного класса HttpServlet, переопределяя его методы doGet и doPost.<br>
 * Сервлет Login отвечает за авторизацию пользователя.
 */
@WebServlet(urlPatterns= {"/login"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	* Метод doGet сервлета Login.<br>
	* Перенаправляет пользователя на главную страницу, вызывая метод doGet сервлета Home.<br>
	* @param request - HTTP запрос
	* @param response - HTTP ответ
	*/
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Метод doGet сервлета Login");  //DEBUG
		
		response.sendRedirect(request.getContextPath() + "/");
	}
	 
	/**
	* Метод doPost сервлета Login. Получает из запроса параметры входа и выполняет проверку данных. <br>
	* Если пароль верный для существующего пользователя, устанавливает сессии атрибут "user" и перенаправляет пользователя
	* на страницу, которую он просматривал.<br>
	* Если введенный пароль неверный, добавляет к запросу параметр "err" и перенаправляет пользователя на страницу, 
	* которую он просматривал.<br>
	* В случае, если пользователь с данным именем не зарегистрирован на сайте, он будет перенаправлен на метод doGet 
	* сервлета Register.<br>
	* <b>Получает параметры запроса: "username", "password", "URI".</b><br>
	* <b>Передает параметры запроса: "err".</b><br>
	* <b>Устанавливает атрибуты сессии: "user".</b>
	* @param request - HTTP запрос
	* @param response - HTTP ответ
	*/
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Метод doPost сервлета Login");  //DEBUG
		 
		String username = request.getParameter("username").stripTrailing();
		String password = request.getParameter("password").stripTrailing();
		
		String URI = request.getParameter("URI") != null ? request.getParameter("URI") : (request.getContextPath() + "/");

		dbFunctions DB = new dbFunctions();
		Connection conn = DB.connectToDB();
		User user = DB.getUser(conn, username);
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		if ( user != null ) {
 
			if ( user.getPassword().equals(password) ) {
				request.getSession().setAttribute("user", user);
				
				response.sendRedirect(URI);
				
			} else {
				response.sendRedirect(URI + "?err=pw");
			}
			 
		} else { response.sendRedirect(request.getContextPath() + "/register"); } 
	
	}
	 
}