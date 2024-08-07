<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="models.*" %>

<% ArrayList<Article> mainNews = (ArrayList<Article>) request.getAttribute("mainNews"); %>
<% String today = new SimpleDateFormat("d MMMM").format(new Date()); %>

<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/WEB-INF/stdheading.jsp" />
		<title>Главное</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/header.jsp" />
		
		<h1 class="mainHeader">Главное на <%= today %></h1>
		<div id="mainNews">
			<div id="firstTitle">
				<% Article main = mainNews.get(0); %>
				<% if ( !main.getImage().isBlank() ) { %>
					<div>
						<img src="<%= main.getImage() %>" class="bigPhoto" />
					</div>
				<% } else { %>
					<div class="articleImage noPhoto lobster-regular">
						<p>MurmanNews</p>
					</div>
				<% } %>
				<a href="${pageContext.request.contextPath}/feed/<%= main.getId() %>" class="link" ><h2><%= main.getTitle() %></h2></a>
				<time><%= main.getUpdatedAt() %></time>
			</div>
			
			<div id="mainnewsTitles">
			<% for (int i = 1; i < mainNews.size(); i++) { %>
				<% Article article = mainNews.get(i); %>
				<div class="mainnewsTitle">
					<a href="${pageContext.request.contextPath}/feed/<%= article.getId() %>" class="link" ><h2><%= article.getTitle() %></h2></a>
					<time><%= article.getUpdatedAt() %></time>
				</div>
			<% } %>
			</div>
		</div>
	</body>
</html>