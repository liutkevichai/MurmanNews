<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="profileForms">
	<%-- Форма для изменения логина и аватара. --%>
	<h2 class="mainHeader">Редактировать профиль</h2>
	<form class="inputForm" action="${pageContext.request.contextPath}/profile" method="POST" accept-charset="utf-8">
		<input type="hidden" name="form" value="form1">
		<input type="hidden" name="oldUsername" value="${param.oldUsername}">
		<div class="item">
			<label class="enrtyLabel" for="newUsername">Изменить логин:</label><br>
			<input class="enrtyField" type="text" id="newUsername" name="newUsername" value="${param.newUsername}" />
		</div>
		<div class="item">
			<label class="enrtyLabel" for="newAvatar">Изменить аватар (ссылка на изображение):</label><br>
			<input class="enrtyField" type="url" id="newAvatar" name="newAvatar" value="${param.newAvatar}" placeholder="https://" pattern="https?://.+" />
		</div>
		
		<div class="successMessage">
			<% if ( request.getParameter("un") != null ) { %>
				<p>Имя пользователя успешно обновлено.</p>
			<% } %>
			<% if ( request.getParameter("av") != null ) { %>
				<p>Аватар успешно обновлен. Если аватар не появился на сайте, убедитесь, что ссылка на изображение рабочая.</p>
			<% } %>
		</div>
		<div class="errorMessage">
			${errorMessage_name}
		</div>
		
		<input class="button" type="submit" value="Отправить" />
	</form>
	
	<%-- Форма для изменения пароля. Требуется ввести старый и новый пароль. --%>
	<h2 class="mainHeader">Изменить пароль</h2>
	<form class="inputForm" action="${pageContext.request.contextPath}/profile" method="POST" accept-charset="utf-8">
		<input type="hidden" name="form" value="form2">
		<div class="item">
			<label class="enrtyLabel" for="oldPassword">Старый пароль *:</label><br>
			<input class="enrtyField" type="password" id="oldPassword" name="oldPassword" required/>
		</div>
		<div class="item">
			<label class="enrtyLabel" for="newPassword">Новый пароль: *:</label><br>
			<input class="enrtyField" type="password" id="newPassword" name="newPassword" pattern=".{6,50}" title="Пароль должен содержать от 6 до 50 символов." required/>
		</div>

		<div class="successMessage">
			<% if ( request.getParameter("pw") != null ) { %>
				<p>Пароль успешно обновлен.</p>
			<% } %>
		</div>
		<div class="errorMessage">
			${errorMessage_oldpass}${errorMessage_newpass}
		</div>
		
		<input class="button" type="submit" value="Отправить" />
	</form>
</div>