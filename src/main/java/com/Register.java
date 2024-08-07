package com;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import models.dbFunctions;
import models.User;

import service.PathProvider;

/**
 * Класс Register наследует от абстрактного класса HttpServlet, переопределяя его методы doGet, doPost и doPut.<br>
 * Сервлет Register отвечает за регистрацию новых пользователей.
 */
@WebServlet(urlPatterns= {"/register", "/register/edit"})
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	* Метод doGet сервлета Register.<br>
	* Если пользователь не вошел под своим аккаунтом, его запрос будет передан на страницу /registration_form.jsp.<br>
	* В обратном случае, пользователь будет перенаправлен на главную страницу.<br>
	* <b>Получает атрибут сессии "user".</b>
	* @param request - HTTP запрос
	* @param response - HTTP ответ
	*/
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Метод doGet сервлета Register"); //DEBUG
		
		if (request.getSession().getAttribute("user") == null) {
			request.getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
			
		} else {
			response.sendRedirect(request.getContextPath() + "/");
		}
	}
	
	/**
	* Метод doPost сервлета Register. Получает из запроса параметры формы регистрации и выполняет проверку данных. <br>
	* Если логин и пароль являются допустимыми, создает нового пользователя и передает запрос методу doPost сервлета Login,
	* автоматически выполняя вход.<br>
	* В обратном случае, устанавливает запросу атрибут "regErrorMessage" и передает его на страницу /registration_form.jsp.<br>
	* Если Post-запрос пришел по адресу /register/edit, вызывает метод doPut.<br>
	* <b>Получает параметры запроса: "username", "password", "role", "avatar".</b><br>
	* <b>Устанавливает атрибуты запроса: "regErrorMessage_name", "regErrorMessage_pass".</b>
	* @param request - HTTP запрос
	* @param response - HTTP ответ
	*/
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Метод doPost сервлета Register"); //DEBUG
		
		if ( PathProvider.getEndpoint(request, -1).equals("edit") ) {
			doPut(request, response);
			
		} else {
			String username = request.getParameter("username").stripTrailing();
			String password = request.getParameter("password").stripTrailing();
			String avatar = request.getParameter("avatar");
			if ( avatar != null ) { 
				avatar.stripTrailing();
			} else {
				avatar = "https://i.imgur.com/DGgiYhO.png";
			}
			String role = request.getParameter("role");
			
			dbFunctions DB = new dbFunctions();
			Connection conn = DB.connectToDB();
			
			if ( DB.getUser(conn, username) != null ) {
				request.setAttribute("regErrorMessage_name", "Данное имя пользователя занято: " + username);
				request.getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
			
			} else {
				
				if ( !Pattern.matches("^[A-Za-z0-9!@#$%^&*()_+\\-=\\[\\]{}|;:'\",.<>?/`~]{1,60}$", username) ) {
					request.setAttribute("regErrorMessage_name", "Логин может содержать только латинские буквы, цифры и спецальные символы. Логин не может быть длинее 60 символов и включать пробелы.");		
					request.getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
					
				} else {
				
					if ( !Pattern.matches("^.{6,50}$", password) ) {
						request.setAttribute("regErrorMessage_pass", "Пароль должен содержать от 6 до 50 символов.");
						request.getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
						
					} else {
						DB.createUser(conn, username, password, role, avatar);
						request.getServletContext().getRequestDispatcher("/login").forward(request, response);	
					}
				}
			}
		}

	}
	
	/**
	* Метод doPut сервлета Register. Получает из запроса параметры формы изменения профиля и выполняет проверку данных. <br>
	* Если параметры проходят проверку, передает параметры запроса: "un=y" (если изменено имя) и/или 
	* "av=y" (если изменен аватар) либо "pw=y" (если изменен пароль), вносит изменения в профиль и перенаправляет 
	* пользователя на метод doGet сервлета Profile.<br>
	* Если параметры не проходят проверку, устанавливает запросу соответствующий атрибут "errorMessage_{type}" и 
	* передает запрос на страницу /profile.jsp.<br>
	* <b>Получает параметры запроса: "oldUsername", "newUsername", "newAvatar", "oldPassword", "newPassword".</b><br>
	* <b>Передает параметры запроса: "un=y", "av=y".</b>
	* <b>Устанавливает атрибуты запроса:"errorMessage_name", "errorMessage_newpass", "errorMessage_oldpass".</b>
	* <b>Получает атрибут сессии "user".</b>
	* @param request - HTTP запрос
	* @param response - HTTP ответ
	*/
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Метод doPut сервлета Register"); //DEBUG
		
		User user = (User) request.getSession().getAttribute("user");
		request.setAttribute("profile", user);
		
		dbFunctions DB = new dbFunctions();
		Connection conn = DB.connectToDB();
		
		if ( request.getParameter("form").equals("form1") ) {
			
			String oldUsername = request.getParameter("oldUsername");
			String newUsername = request.getParameter("newUsername");
			String newAvatar = request.getParameter("newAvatar");
			
			String param1 = null;
			String param2 = null;
			
			if ( newUsername != "" ) {
				newUsername.stripTrailing();
				
				if ( DB.getUser(conn, newUsername) != null ) {
					request.setAttribute("errorMessage_name", "Данное имя пользователя занято: " + newUsername);
					
				} else if ( !Pattern.matches("^[A-Za-z0-9!@#$%^&*()_+\\-=\\[\\]{}|;:'\",.<>?/`~]{1,60}$", newUsername) ) {
					request.setAttribute("errorMessage_name", "Логин может содержать только латинские буквы, цифры и спеицальные символы. Логин не может быть длинее 60 символов и включать пробелы.");

				} else {
					DB.updateUser(conn, oldUsername, newUsername, null, null);
					user = DB.getUser(conn, newUsername);
					request.getSession().setAttribute("user", user);
					
					param1 = "un=y";
				}
			}

			if ( newAvatar != "" ) {
				newAvatar.stripTrailing();
				DB.updateUser(conn, oldUsername, null, newAvatar, null);
				user = DB.getUser(conn, oldUsername);
				request.getSession().setAttribute("user", user);

				param2 = "av=y";
			}
				
			String params = "?";
			if ( param1 != null) {
				params += param1;
				if ( param2 != null) {
					params += "&" + param2;
				}
			} else { params += param2; }
			
			if ( request.getAttribute("errorMessage_name") != null ) {
				request.getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
				
			} else {
				response.sendRedirect(request.getContextPath() + "/profile/" + user.getUsername() + params);
			}	
			
		} else if ( request.getParameter("form").equals("form2") ){
			String oldPassword = request.getParameter("oldPassword");
			String newPassword = request.getParameter("newPassword");
	
			if ( user.getPassword().equals(oldPassword) ) {
				
				if ( !Pattern.matches("^.{6,50}$", newPassword) ) {
					request.setAttribute("errorMessage_newpass", "Пароль должен содержать от 6 до 50 символов.");
					request.getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
					
				} else { 
					DB.updateUser(conn, user.getUsername(), null, null, newPassword);
					
					response.sendRedirect(request.getContextPath() + "/profile/" + user.getUsername() + "?pw=y");
				}
				
			} else { 
				request.setAttribute("errorMessage_oldpass", "Старый пароль указан неверно.");
				request.getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
			}
		}
		
	}
			
}