<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.*" %>

<!DOCTYPE html>
<% User user = (User) session.getAttribute("user"); %>
<html>
	<head>
		<jsp:include page="/WEB-INF/stdheading.jsp" />
		<title>Новостная лента</title>
	</head>
	<jsp:include page="/WEB-INF/header.jsp" />
	<body>
		<h1 class="mainHeader" id="feedHeader">Новости Мурманской области</h1>
		
		<%-- Блок с контентом. Виден всем пользователям. Статьи отображаются в порядке от новых к старым. --%>
		<% ArrayList<Article> reversedArticles = (ArrayList<Article>) request.getAttribute("reversedArticles"); %>
		<% for ( Article article: reversedArticles ) { %>
			<article>
				<jsp:include page="/WEB-INF/article_inner.jsp">
					<jsp:param name="articleTitle" value="<%= article.getTitle() %>" />
					<jsp:param name="articleAuthor" value="<%= article.getAuthor() %>" />
					<jsp:param name="articleDate" value="<%= article.getUpdatedAt()  %>" />
					<jsp:param name="articleImage" value="<%= article.getImage() %>" />
					<jsp:param name="articleContent" value="<%= article.getContents() %>" />
				</jsp:include>
				<a href="${pageContext.request.contextPath}/feed/<%= article.getId() %>" id="link">Комментарии</a>
			</article>
		<% } %>
	</body>
</html>
