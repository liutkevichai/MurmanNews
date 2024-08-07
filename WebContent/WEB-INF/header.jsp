<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.User" %>

<% User user = (User) session.getAttribute("user"); %>

<header id="header">
	<a href="${pageContext.request.contextPath}" id="murmanNews" class="lobster-regular">MurmanNews</a>
	<%-- Шапка для зарегистрированных пользователей. --%>
	<% if ( user != null) { %>
		<div id="headerWelcome">
			Здравствуйте, <b><a href="${pageContext.request.contextPath}/profile/${user.username}" class="link">${user.username}</a></b>! Вы вошли как ${user.role}.<br>
			<a href="${pageContext.request.contextPath}/logout" class="link">Выйти</a>
		</div>
		<a href="${pageContext.request.contextPath}/profile/${user.username}" >
			<img src="${user.avatar}" id="userAvatar" class="avatar"/>
		</a>
	<%-- Шапка для незарегистрированных пользователей. --%>
	<% } else { %>
		<div id="testAccounts" class="errorMessage"> Тестовые аккаунты: логин iVanovna, пароль servletslab111 (автор); логин Teacup, пароль tusuruni345 (читатель).</div>
		<jsp:include page="/WEB-INF/loginform.jsp" />	
	<% } %>
	
	<%-- Навигация сайта. --%>
	<nav>
		<a href="${pageContext.request.contextPath}" class="navLink">Главное</a>
		<a href="${pageContext.request.contextPath}/feed" class="navLink">Лента</a>
		<% if ( user != null) { %>
			<a href="${pageContext.request.contextPath}/profile" class="navLink">Профиль</a>	
			<% if ( user.getRole().equals("автор")) { %>
				<a href="${pageContext.request.contextPath}/post" class="navLink">Создать новость</a>
			<% } %>
		<% } else { %>
			<a href="${pageContext.request.contextPath}/register" class="navLink">Регистрация</a>
		<% } %>
	</nav>
</header>