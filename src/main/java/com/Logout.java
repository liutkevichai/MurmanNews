package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Класс Logout наследует от абстрактного класса HttpServlet, переопределяя его метод doGet.<br>
 * Сервлет Logout отвечает за выход из аккаунта пользователя.
 */
@WebServlet(urlPatterns= {"/logout"})
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	* Метод doGet сервлета Logout. Аннулирует сессию пользователя.<br>
	* После выхода из аккаунта, пользователь будет перенаправлен на главную страницу сайта.<br>
	* @param request - HTTP запрос
	* @param response - HTTP ответ
	*/
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("Метод doGet сервлета Logout");  //DEBUG
    	request.getSession().invalidate();
    	
    	response.sendRedirect(request.getContextPath() + "/");
	}

}
