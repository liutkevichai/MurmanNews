package models;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Класс пользователь со свойствами id, username, password, role, avatar и registerDate.
 */
public class User {
	/** Поле "идентификатор пользователя". */
	private Integer id;
	/** Поле "имя пользователя". */
	private String username;
	/** Поле "пароль пользователя". */
	private String password;
	/** Поле "роль пользователя". */
	private String role;
	/** Поле "аватар пользователя". */
	private String avatar;
	/** Поле "дата регистрации". */
	private Date registerDate;
	
	/**
	* @param id - идентификатор пользователя
	* @param username - имя пользователя
	* @param password - пароль пользователя
	* @param role - роль пользователя ("автор"/"читатель")
	* @param avatar - аватар пользователя
	* @param registerDate - дата регистрации
	*/
	public User(Integer id, String username, String password, String role, String avatar, Date registerDate) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.avatar = avatar;
		this.registerDate = registerDate;
	}
	
	/**
	* Функция получения значения поля {@link User#id}
	* @return возвращает идентификатор пользователя
	*/
	public Integer getId() {
		return id;
	}

	/**
	* Функция получения значения поля {@link User#username}
	* @return возвращает имя пользователя
	*/
	public String getUsername() {
		return username;
	}
	
	/**
	* Функция получения значения поля {@link User#password}
	* @return возвращает пароль пользователя
	*/
	public String getPassword() {
		return password;
	}
	
	/**
	* Функция получения значения поля {@link User#role}
	* @return возвращает роль пользователя ("автор"/"читатель")
	*/
	public String getRole() {
		return role;
	}
	
	/**
	* Функция получения значения поля {@link User#avatar}
	* @return возвращает ссылку на аватар пользователя
	*/
	public String getAvatar() {
		return avatar;
	}
	
	/**
	* Функция получения значения поля {@link User#registerDate}
	* @return возвращает дату регистрации пользователя
	*/
	public String getRegisterDate() {
		String FregisterDate = new SimpleDateFormat("dd.MM.yyyy").format(registerDate);
		return FregisterDate;
	}

	
}
