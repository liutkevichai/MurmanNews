<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%-- Зарегистрированные пользователи будут перенаправлены на главную страницу сайта. --%>
<% if (session.getAttribute("user") != null) { %>
	<jsp:forward page="${pageContext.request.contextPath}" />
<% } %>
	<head>
		<jsp:include page="/WEB-INF/stdheading.jsp" />
		<title>Зарегистрироваться</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/header.jsp" />
		
		<%-- Форма регистрации. --%>
		<div class="formContainer" id="registrationForm">
			<h1 class="mainHeader">Регистрация</h1>
			<p class="formIntro helvetica">Зарегистрируйтесь, чтобы размещать статьи на сайте и оставлять комментарии.<br>После успешной регистрации вы будете перенаправлены на главную страницу.</p>
			<jsp:include page="/WEB-INF/registerform.jsp">
				<jsp:param name="username" value="${param.username}" />
				<jsp:param name="avatar" value="${param.avatar}" />
				<jsp:param name="errorMessage" value="${param.errorMessage}" />
			</jsp:include>
		</div>
		<br>
	</body>
</html>