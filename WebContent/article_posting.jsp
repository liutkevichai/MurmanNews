<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.User" %>

<!DOCTYPE html>
<% User user = (User) session.getAttribute("user"); %>
<html>
	<%-- Пользователи типа "гость" или "читатель" будут перенаправлены на главную страницу сайта. --%>
	<% if ( user == null || user.getRole().equals("читатель")) { %>
		<jsp:forward page="${pageContext.request.contextPath}" />
	<% } %>

	<head>
		<jsp:include page="/WEB-INF/stdheading.jsp" />
		<title>Создать новость</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/header.jsp" />
		
		<% String action = request.getContextPath() + "/post"; %>	
		
		<%-- Форма для постинга статьи. --%>
		<div class="formContainer" id="articleForm">
			<h1 class="mainHeader">Создайте новый пост</h1>
			<jsp:include page="/WEB-INF/articleform.jsp">
				<jsp:param name="action" value="<%= action %>" />
				<jsp:param name="content" value="Введите текст вашей новости." />
			</jsp:include>
		</div>
	</body>
</html>