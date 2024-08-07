<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<form class="inputForm" action="${param.action}" method="POST" accept-charset="utf-8">
	<input type="hidden" name="articleId" value="${param.articleId}">
	<div class="item" id="autoAuthor">
		<label class="enrtyLabel" for="author">Автор:</label>
		<input class="enrtyFieldDisabled" id="author" type="text" name="author" value="${user.username}" />
	</div>		
	<div class="item">
		<label class="enrtyLabel" for="title">Заголовок статьи:</label><br>
		<input class="enrtyField" type="text" id="title" name="title" value="${param.title}" required />
	</div>
	<div class="item">
		<textarea class="enrtyField" name="contents" required>${param.contents}</textarea>
	</div>
	<div class="item">
		<label class="enrtyLabel" for="image">Ссылка на изображение:</label><br>
		<input class="enrtyField" type="url" id="image" name="image" value="${param.image}" placeholder="https://" pattern="https?://.+" />
	</div>
	<input class="button" type="submit" value="Отправить"/>
	<div id="errorMessage">
		<p>${errorMessage}</p>
	</div>
</form>