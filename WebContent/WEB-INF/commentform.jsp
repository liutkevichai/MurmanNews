<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<form class="inputForm" action="${pageContext.request.contextPath}/comment" method="POST" accept-charset="utf-8">
	<input type="hidden" name="articleId" value="${param.articleId}">
	<input type="hidden" name="author" value="${param.author}" />
	<input type="hidden" name="authorAvatar" value="${param.authorAvatar}" />
	<div class="item">
		<textarea class="enrtyField" id="commentTextarea" name="comment" required>Введите текст комментария.</textarea><br>
	</div>
	<input class="button" type="submit" value="Отправить"/>
</form>