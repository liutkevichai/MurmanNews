<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h1 class="articleTitle">${param.articleTitle}</h1>
<div class="articleInfo">
	<a href="${pageContext.request.contextPath}/profile/${param.articleAuthor}" class="link">Автор: ${param.articleAuthor}</a>
	<time>Последнее изменение: ${param.articleDate}</time>
</div>
<% if ( request.getParameter("articleImage") != "") { %>
	<div class="articleImage">
		<img src="${param.articleImage}" class="bigPhoto" />
	</div>
<% } else { %>
	<div class="articleImage noPhoto lobster-regular">
		<p>MurmanNews</p>
	</div>
<% } %>
<p>${param.articleContent}</p>
