package com;

import java.io.IOException;
import java.sql.Connection;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.dbFunctions;
import service.PathProvider;

/**
 * Класс Profile наследует от абстрактного класса HttpServlet, переопределяя его методы doGet и doPost.<br>
 * При переходе в профиль пользователя, сервлет Profile передает запрос на страницу /profile.jsp.
 */
@WebServlet(urlPatterns= {"/profile", "/profile/*"})
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	* Метод doGet сервлета Profile.<br>
	* В любом случае перенаправляет пользователя на страницу /profile.jsp.<br>
	* <b>Устанавливает атрибуты запроса: "profile", "URI".</b><br>
	* <b>Получает атрибут сессии "user".</b>
	* @param request - HTTP запрос
	* @param response - HTTP ответ
	*/ 
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Метод doGet сервлета Profile"); //DEBUG
		
		User user = (User) request.getSession().getAttribute("user");
		
		dbFunctions DB = new dbFunctions();
		Connection conn = DB.connectToDB();
		
		if ( request.getRequestURI().equals( request.getContextPath() + "/profile") ) {
			
			if ( user != null ) {
				request.setAttribute("profile", user);
				request.setAttribute("commentCount", DB.getCommentCount(conn, user.getUsername()));
				
				if ( user.getRole().equals("автор") ) { 
					request.setAttribute("userArticles", DB.getUserArticles(conn, user.getUsername()));
				}
				
				request.setAttribute("URI", request.getRequestURI());
				
				request.getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
				
			} else { response.sendRedirect(request.getContextPath() + "/"); }
		
    	} else  if ( Pattern.matches(request.getContextPath() + "/profile/[A-Za-z0-9!@#$%^&*()_+\\-=\\[\\]{}|;:'\",.<>?/`~]+$", request.getRequestURI()) ) {

    		if ( DB.getUser(conn, PathProvider.getEndpoint(request, -1)) != null ) {
    			
    			User profile = DB.getUser(conn, PathProvider.getEndpoint(request, -1));
    		
    			request.setAttribute("profile", profile);
    			request.setAttribute("commentCount", DB.getCommentCount(conn, profile.getUsername()));
    			
				if ( profile.getRole().equals("автор") ) { 
					request.setAttribute("userArticles", DB.getUserArticles(conn, profile.getUsername()));
				}
    			
    			request.setAttribute("URI", request.getRequestURI());
    			
    			request.getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
    		}

    	} else { response.sendRedirect(request.getContextPath() + "/notfound.jsp"); }

	}
    
	/**
	* Метод doPost сервлета Profile. 
	* Перенаправляет пользователя на метод doPost сервлета Register.<br>
	* @param request - HTTP запрос
	* @param response - HTTP ответ
	*/
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Метод doPost сервлета Profile"); //DEBUG
		
		request.getServletContext().getRequestDispatcher("/register/edit").forward(request, response);
	}

}
