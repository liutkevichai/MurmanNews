<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<form class="inputForm" id="registrationFormInner" action="${pageContext.request.contextPath}/register" method="POST" accept-charset="utf-8">
	<div class="item">
		<label class="enrtyLabel" for="username">Логин *:</label><br>
		<input class="enrtyField" type="text" id="username" name="username" value="${param.username}" required />
		<div class="errorMessage">${regErrorMessage_name}</div>
	</div>
	<div class="item">
		<label class="enrtyLabel" for="password">Пароль *:</label><br>
		<input class="enrtyField" type="password" id="password" name="password" pattern=".{6,50}" title="Пароль должен содержать от 6 до 50." required/>
		<div class="errorMessage">${regErrorMessage_pass}</div>
	</div>
	<div class="item">
		<label class="enrtyLabel" for="avatar">Аватар (ссылка на изображение):</label><br>
		<input class="enrtyField" type="url" id="avatar" name="avatar" value="${param.avatar}" placeholder="https://" pattern="https?://.+" />
	</div>
	<div class="item">
		<label class="enrtyLabel" for="role">Тип пользователя *:</label><br>	
		<select class="enrtyField"  id="role" name="role" required>
			<option value="" selected disabled>Выберите роль на сайте:</option>
			<option value="читатель">Читатель</option>
			<option value="автор">Автор</option>
		</select>
	</div>
	<input class="button" type="submit" value="Отправить" />
</form>