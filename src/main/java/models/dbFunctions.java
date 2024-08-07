package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;

import java.util.ArrayList;

public class dbFunctions {
	public Connection connectToDB() {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/MurmanNews", "postgres", "password");
		
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return conn;
	}
    
	/// Model User
	
	public User getUser(Connection conn, String username) {
		Statement statement;
		ResultSet rs;
		User user = null;
		try {
			statement = conn.createStatement();
			String query = String.format("SELECT * FROM users WHERE username = '%s';", username);
			rs = statement.executeQuery(query);
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String password = rs.getString("password");
				String role = rs.getString("role");
				String avatar = rs.getString("avatar");
				Date registerDate = rs.getDate("register_date");
				user = new User(id, username, password, role, avatar, registerDate);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return user;
	}
    
	public boolean createUser(Connection conn, String username, String password, String role, String avatar) {
		Statement statement;
		boolean res = false;
		try {
			statement = conn.createStatement();
			String query = String.format("INSERT INTO users (username, password, role, avatar) VALUES "
					+ "('%s', '%s', '%s', '%s');", username, password, role, avatar);
			statement.executeUpdate(query);
			res = true;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return res;
	}
	
	public boolean updateUser(Connection conn, String username, String newUsername, String newAvatar, String newPassword) {
		Statement statement;
		boolean res = false;
		String query = null;
		try {
			statement = conn.createStatement();
			
			if ( newUsername != null ) {
				query = String.format("UPDATE users SET username = '%s' WHERE username = '%s';", newUsername, username);
				statement.executeUpdate(query);
				res = true;
			}
			
			if ( newAvatar != null ) {
				query = String.format("UPDATE users SET avatar = '%s' WHERE username = '%s';", newAvatar, username);
				statement.executeUpdate(query);
				res = true;
			}
			
			if ( newPassword != null ) {
				query = String.format("UPDATE users SET password = '%s' WHERE username = '%s';", newPassword, username);
				statement.executeUpdate(query);
				res = true;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return res;
	}
	
	public boolean deleteUser(Connection conn, String username) {
		Statement statement;
		boolean res = false;
		try {
			statement = conn.createStatement();
			String query = String.format("DELETE FROM users WHERE username = '%s';", username);
			statement.executeUpdate(query);
			res = true;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return res;
	}
	
	/// Model Article
	
	public Article getArticle(Connection conn, Integer articleId) {
		Statement statement;
		ResultSet rs;
		Article article = null;
		try {
			statement = conn.createStatement();
			String query = String.format("SELECT * FROM articles WHERE id = '%d';", articleId);
			rs = statement.executeQuery(query);
			
			while (rs.next()) {
				String author = rs.getString("author");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				String image = rs.getString("image");
				Date createdAt = rs.getDate("created_at");
				Date updatedAt = rs.getDate("updated_at");
				article = new Article(articleId, author, title, contents, image, createdAt, updatedAt);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return article;
	}
	
	public ArrayList<Article> getArticles(Connection conn) {
		Statement statement;
		ResultSet rs;
		ArrayList<Article> resList = new ArrayList<Article>();
		try {
			statement = conn.createStatement();
			String query = String.format("SELECT * FROM articles ORDER BY id ASC;");
			rs = statement.executeQuery(query);
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String author = rs.getString("author");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				String image = rs.getString("image");
				Date createdAt = rs.getDate("created_at");
				Date updatedAt = rs.getDate("updated_at");
				resList.add(new Article(id, author, title, contents, image, createdAt, updatedAt));
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return resList;
	}
	
	public ArrayList<Article> getArticlesReversed(Connection conn) {
		Statement statement;
		ResultSet rs;
		ArrayList<Article> resList = new ArrayList<Article>();
		try {
			statement = conn.createStatement();
			String query = String.format("SELECT * FROM articles ORDER BY id DESC;");
			rs = statement.executeQuery(query);
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String author = rs.getString("author");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				String image = rs.getString("image");
				Date createdAt = rs.getDate("created_at");
				Date updatedAt = rs.getDate("updated_at");
				resList.add(new Article(id, author, title, contents, image, createdAt, updatedAt));
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return resList;
	}
	
	
	public ArrayList<Article> getArticlesReversedLimited(Connection conn, Integer limit) {
		Statement statement;
		ResultSet rs;
		ArrayList<Article> resList = new ArrayList<Article>();
		try {
			statement = conn.createStatement();
			String query = String.format("SELECT * FROM articles ORDER BY id DESC LIMIT '%d';", limit);
			rs = statement.executeQuery(query);
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String author = rs.getString("author");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				String image = rs.getString("image");
				Date createdAt = rs.getDate("created_at");
				Date updatedAt = rs.getDate("updated_at");
				resList.add(new Article(id, author, title, contents, image, createdAt, updatedAt));
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return resList;
	}
	
	public ArrayList<Article> getUserArticles(Connection conn, String username) {
		Statement statement;
		ResultSet rs;
		ArrayList<Article> resList = new ArrayList<Article>();
		try {
			statement = conn.createStatement();
			String query = String.format("SELECT * FROM articles WHERE author = '%s' ORDER BY created_at DESC;", username);
			rs = statement.executeQuery(query);
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String author = rs.getString("author");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				String image = rs.getString("image");
				Date createdAt = rs.getDate("created_at");
				Date updatedAt = rs.getDate("updated_at");
				resList.add(new Article(id, author, title, contents, image, createdAt, updatedAt));
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return resList;
	}
	
	public boolean createArticle(Connection conn, String author, String title, String contents, String image) {
		Statement statement;
		boolean res = false;
		try {
			statement = conn.createStatement();
			String query = String.format("INSERT INTO articles (author, title, contents, image) VALUES "
					+ "('%s', '%s', '%s', '%s');", author, title, contents, image);
			statement.executeUpdate(query);
			res = true;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return res;
	}
	
	public boolean updateArticle(Connection conn, Integer articleId, String title, String contents, String image) {
		Statement statement;
		boolean res = false;
		String query = null;
		try {
			statement = conn.createStatement();
			
			if ( title != null ) {
				query = String.format("UPDATE articles SET title = '%s' WHERE id = '%d';", title, articleId);
				statement.executeUpdate(query);
				res = true;
			}
			
			if ( contents != null ) {
				query = String.format("UPDATE articles SET contents = '%s' WHERE id = '%d';", contents, articleId);
				statement.executeUpdate(query);
				res = true;
			}
			
			if ( image != null ) {
				query = String.format("UPDATE articles SET image = '%s' WHERE id = '%d';", image, articleId);
				statement.executeUpdate(query);
				res = true;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return res;
	}
	
	public boolean deleteArticle(Connection conn, Integer id) {
		Statement statement;
		boolean res = false;
		try {
			statement = conn.createStatement();
			String query = String.format("DELETE FROM articles WHERE id = '%d';", id);
			statement.executeUpdate(query);
			res = true;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return res;
	}
	
	// Model Comment
	
	public Comment getComment(Connection conn, Integer commentId) {
		Statement statement;
		ResultSet rs;
		Comment comment = null;
		try {
			statement = conn.createStatement();
			String query = String.format("SELECT * FROM comments WHERE id = '%d';", commentId);
			rs = statement.executeQuery(query);
			
			while (rs.next()) {		
				String author = rs.getString("author");
				Integer articleId = rs.getInt("article_id");
				String contents = rs.getString("contents");
				Date createdAt = rs.getDate("created_at");
				comment = new Comment(commentId, author, articleId, contents, createdAt);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return comment;
	}
	
	public ArrayList<Comment> getComments(Connection conn, Integer articleId) {
		Statement statement;
		ResultSet rs;
		ArrayList<Comment> resList = new ArrayList<Comment>();
		try {
			statement = conn.createStatement();
			String query = String.format("SELECT * FROM comments WHERE article_id = '%d' ORDER BY created_at DESC;", articleId);
			rs = statement.executeQuery(query);
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String author = rs.getString("author");
				String contents = rs.getString("contents");
				Date createdAt = rs.getDate("created_at");
				resList.add(new Comment(id, author, articleId, contents, createdAt));
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return resList;
	}
	
	public Integer getCommentCount(Connection conn, String username) {
		Statement statement;
		ResultSet rs;
		Integer count = 0;
		try {
			statement = conn.createStatement();
			String query = String.format("SELECT count(*) FROM comments WHERE author = '%s';", username);
			rs = statement.executeQuery(query);
			
			while (rs.next()) {
				count += rs.getInt("count");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return count;
	}
	
	public boolean createComment(Connection conn, String author, Integer articleId, String contents) {
		Statement statement;
		boolean res = false;
		try {
			statement = conn.createStatement();
			String query = String.format("INSERT INTO comments (author, article_id, contents) VALUES "
					+ "('%s', '%d', '%s');", author, articleId, contents);
			statement.executeUpdate(query);
			res = true;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return res;
	}
	
	public boolean deleteComment(Connection conn, Integer id) {
		Statement statement;
		boolean res = false;
		try {
			statement = conn.createStatement();
			String query = String.format("DELETE FROM comments WHERE id = '%d';", id);
			statement.executeUpdate(query);
			res = true;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return res;
	}
	
}