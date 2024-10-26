<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% String URI = (String) request.getAttribute("URI"); %>
<% if ( URI == null ) { URI = request.getContextPath(); } %>

<form id="loginForm" action="${pageContext.request.contextPath}/login" method="POST" accept-charset="utf-8">
	<input type="hidden" name="URI" value=<%= URI %> />
	<input class="enrtyField" type="text" name="username" placeholder="Логин" required />
	<input class="enrtyField" type="password" name="password" placeholder="Пароль" required />
	<input class="button" type="submit" value="Войти" />
	<div class="errorMessage">
		<% if ( request.getParameter("err") != null ) { %>
			Введен неверный пароль.
		<% } %>
	</div>
</form>
