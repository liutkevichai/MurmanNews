<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.*" %>

<!DOCTYPE html>
<html>
<% User user = (User) session.getAttribute("user"); %>
<% User profile = (User) request.getAttribute("profile"); %>
	<head>
		<jsp:include page="/WEB-INF/stdheading.jsp" />
		<title>Профиль ${profile.username}</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/header.jsp" />
		
		<%-- Профиль пользователя. Виден всем пользователям. --%>
		<h1 class="mainHeader">Профиль пользователя ${profile.username}</h1>
		<div class="userProfile">
			
			<div id="profileContainer">
				<div id="userImageContainer">
					<img id="userImage" src="<%= profile.getAvatar() %>" />
				</div>
				<div id="profileItemsBlock">
					<table>
						<tbody>
							<tr><td class="profileItem">Дата регистрации:</td><td class="helvetica profileItem"><time class="date">
								<%= profile.getRegisterDate() %>
							</time></td>
							<tr><td class="profileItem">Роль на сайте:</td><td class="helvetica profileItem"><%= profile.getRole() %></td>
							<tr><td class="profileItem">Комментариев:</td><td class="helvetica profileItem"><%= request.getAttribute("commentCount") %></td>
						</tbody>
					</table>
				</div>
			</div>
			
			<%-- Блок со статьями отображается только для профилей типа "автор". --%>
			<% if (profile.getRole().equals("автор")) { %>
				<div id="userArticlesBlock">
					<h2 class="mainHeader">Статьи автора</h2>
					<% ArrayList<Article> userArticles = (ArrayList<Article>) request.getAttribute("userArticles");  %>
					<% for ( Article article: userArticles ) { %>
						<p><a href="${pageContext.request.contextPath}/feed/<%= article.getId() %>" class="link helvetica"><%= article.getTitle() %></a></p>
					<% } %>
				</div>
			<% } %>
			
			<%-- Форма редактирования профиля. Отображается только в собственном профиле пользователя. --%>
			<% if ( user != null && profile.getUsername().equals(user.getUsername()) ) { %>
				<jsp:include page="/WEB-INF/profileform.jsp">
					<jsp:param name="oldUsername" value="${user.username}" />
					<jsp:param name="newUsername" value="${param.newUsername}" />
					<jsp:param name="newAvatar" value="${param.newAvatar}" />
				</jsp:include>
			<% } %>
			
		</div> 
		<br>
	</body>
</html>