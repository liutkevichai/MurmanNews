package models;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
* Класс комментарий со свойствами id, author, articleId, contents и createdAt.
*/
public class Comment {
	/** Поле "идентификатор комментария". */
	private Integer id;
	/** Поле "автор комментария". */
	private String author;
	/** Поле "идентификатор статьи". */
	private Integer articleId;
	/** Поле "текст комментария". */
	private String contents;
	/** Поле "дата создания комментария". */
	private Date createdAt;
	
	/** 
	* @param id - идентификатор комментария
	* @param author - автор комментария
	* @param articleId - идентификатор статьи
	* @param contents - текст комментария
	* @param createdAt - дата создания комментария
	*/
	public Comment(Integer id, String author, Integer articleId, String contents, Date createdAt) {
		this.id = id;
		this.author = author;
		this.articleId =  articleId;
		this.contents = contents;
		this.createdAt = createdAt;
	}
	
	/**
	* Функция получения значения поля {@link Comment#id}
	* @return возвращает идентификатор комментария.
	*/
	public Integer getId() {
		return id;
	}	
	
	/**
	* Функция получения значения поля {@link Comment#author}
	* @return возвращает имя автора комментария.
	*/
	public String getAuthor() {
		return author;
	}
	
	/**
	* Функция получения значения поля {@link Comment#articleId}
	* @return возвращает идентификатор статьи.
	*/
	public Integer getArticleId() {
		return articleId;
	}	
	
	/**
	* Функция получения значения поля {@link Comment#contents}
	* @return возвращает текст комментария.
	*/
	public String getContents() {
		return contents;
	}
	
	/**
	* Функция получения значения поля {@link Comment#createdAt}
	* @return возвращает дату создания комментария.
	*/
	public String getCreatedAt() {
		String FcreatedAt = new SimpleDateFormat("dd.MM.yyyy").format(createdAt);
		return FcreatedAt;
	}
	
}
