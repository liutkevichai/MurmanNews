package models;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Класс статья со свойствами id, author, title, contents, image, createdAt и updatedAt.
 */
public class Article {
	/** Поле "идентификатор статьи". */
	private Integer id;
	/** Поле "автор статьи". */
	private String author;
	/** Поле "заголовок статьи". */
	private String title;
	/** Поле "текст статьи". */
	private String contents;
	/** Поле "ссылка на изображение статьи". */
	private String image;
	/** Поле "дата создания статьи". */
	private Date createdAt;
	/** Поле "дата обновления статьи". */
	private Date updatedAt;
	
	/** 
	* @param id - идентификатор статьи
	* @param author - автор статьи
	* @param title - заголовок статьи
	* @param contents - текст статьи
	* @param image - ссылка изображение
	* @param createdAt - дата создания статьи
	* @param updatedAt - дата обновления статьи
	*/
	public Article(Integer id, String author, String title, String contents, String image, Date createdAt, Date updatedAt) {
		this.id = id;
		this.author = author;
		this.title = title;
		this.contents = contents;
		this.image = image;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	/**
	* Функция получения значения поля {@link Article#id}
	* @return возвращает идентификатор статьи
	*/
	public Integer getId() {
		return id;
	}

	/**
	* Функция получения значения поля {@link Article#author}
	* @return возвращает имя автора статьи
	*/
	public String getAuthor() {
		return author;
	}
	
	/**
	* Функция получения значения поля {@link Article#title}
	* @return возвращает заголовок статьи
	*/
	public String getTitle() {
		return title;
	}
	
	/**
	* Функция получения значения поля {@link Article#contents}
	* @return возвращает текст статьи
	*/
	public String getContents() {
		return contents;
	}
	
	/**
	* Функция получения значения поля {@link Article#image}
	* @return возвращает ссылку на изображение
	*/
	public String getImage() {
		return image;
	}
	
	/**
	* Функция получения значения поля {@link Article#createdAt}
	* @return возвращает дату создания статьи
	*/
	public String getCreatedAt() {
		String FcreatedAt = new SimpleDateFormat("dd.MM.yyyy").format(createdAt);
		return FcreatedAt;
	}
	
	/**
	* Функция получения значения поля {@link Article#updatedAt}
	* @return возвращает дату обновления статьи
	*/
	public String getUpdatedAt() {
		String FupdatedAt = new SimpleDateFormat("dd.MM.yyyy").format(updatedAt);
		return FupdatedAt;
	}
	
}
