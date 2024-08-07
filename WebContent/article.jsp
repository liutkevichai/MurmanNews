<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="service.PathProvider" %>
<%@ page import="models.*" %>

<!DOCTYPE html>
<% User user = (User) session.getAttribute("user"); %>
<% Article article = (Article) request.getAttribute("article"); %>
<html>
	<head>
		<jsp:include page="/WEB-INF/stdheading.jsp" />
		<title>${artice.title}</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/header.jsp" />
		
		<%-- Блок со статьей виден всем пользователям. --%>
		<article>
			<jsp:include page="/WEB-INF/article_inner.jsp">
				<jsp:param name="articleTitle" value="<%= article.getTitle() %>" />
				<jsp:param name="articleAuthor" value="<%= article.getAuthor() %>" />
				<jsp:param name="articleDate" value="<%= article.getUpdatedAt() %>" />
				<jsp:param name="articleImage" value="<%= article.getImage() %>" />
				<jsp:param name="articleContent" value="<%= article.getContents() %>" />
			</jsp:include>
		
		<%-- Блок виден только зарегистрированным пользователям. --%>	
		<% if ( user != null ) { %>
			
			<%-- Блок виден только автору статьи. --%>	
			<% if ( user.getRole().equals("автор") && user.getUsername().equalsIgnoreCase(article.getAuthor()) ) { %>
			
				<%-- Форма редактирования статьи видна только в режиме редактирования. --%>	
				<% if ( request.getParameter("editMode") != null ) { %>
					<% String action = request.getContextPath() + "/feed/" + article.getId(); %>		
					<div class="formContainer" id="editMode">
						<h2 class="mainHeader">Редактировать статью</h2>
						<jsp:include page="/WEB-INF/articleform.jsp">
							<jsp:param name="action" value="<%= action %>" />
							<jsp:param name="articleId" value="<%= article.getId() %>" />
							<jsp:param name="title" value="<%= article.getTitle() %>" />
							<jsp:param name="contents" value="<%= article.getContents() %>" />
							<jsp:param name="image" value="<%= article.getImage() %>" />
						</jsp:include>
					</div>
				<% } else { %>
					<a href="${pageContext.request.contextPath}/feed/<%= article.getId() %>?editMode=true" class="link">Редактировать</a>
				<% } %>
					<a href="${pageContext.request.contextPath}/feed/<%= article.getId() %>?delete=<%= article.getId() %>" class="link">Удалить</a>
				
			<% } %>
			</article>
			
			<%-- Форма для комментирования. --%>
			<div class="formContainer" id="commentForm">
				<h2 class="mainHeader">Оставить комментарий</h2>
				<jsp:include page="/WEB-INF/commentform.jsp">
					<jsp:param name="articleId" value="<%= article.getId() %>" />
					<jsp:param name="author" value="<%= user.getUsername() %>" />
					<jsp:param name="authorAvatar" value="<%= user.getAvatar() %>" />
				</jsp:include>
			</div>

		<% } else { %>
			</article>
			
			<div id="loginRegister">
				<a href="#header" class="link" >Войдите</a> или <a href="${pageContext.request.contextPath}/register" class="link">зарегистрируйтесь</a>, чтобы оставлять комментарии.
			</div>
			
		<% } %>
			
			<%-- Комментарии к статье видны всем пользователям. --%>	
			<div id="commentSection">
				<h3>Комментарии</h3>
				<% Map<Integer, User> commentators = (Map<Integer, User>) request.getAttribute("commentators"); %>
				<% ArrayList<Comment> articleComments = (ArrayList<Comment>) request.getAttribute("comments"); %>
				<% for ( Comment comment: articleComments ) { %>
				<div class="comment">
					<div class="articleInfo">
						<a href="${pageContext.request.contextPath}/profile/<%= comment.getAuthor() %>" class="link"><%= comment.getAuthor() %></a>
						<time ><%= comment.getCreatedAt() %></time>
					</div>
					<div class="commentContent">
						<img src=<%= commentators.get(comment.getId()).getAvatar() %> class="avatar" />
						<p><%= comment.getContents() %></p>
					</div>
					<% if ( user != null && user.getUsername().equals(comment.getAuthor()) ) { %>
						<div class="justifyEnd">
							<a href="${pageContext.request.contextPath}/comments?delete=<%= comment.getId() %>" class="link">Удалить</a>
						</div>
					<% } %>
				</div>
				<% } %>
			</div>	
	</body>
</html>