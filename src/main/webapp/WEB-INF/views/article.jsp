<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored = "false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Article Page</title>
 <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
<link href="<c:url value='resources/Article.css' />" rel="stylesheet"></link>
<script>tinymce.init({ selector:'textarea' });</script>
</head>
<body>

<c:forEach var="article" items="${listArticle}" varStatus="status">
               
                    <span class="title">${article.id}</span>
                    <span class="title"><a target="blank" href="articledetails?articleid=${article.id}">${article.title}</a></span>
                    <span class="teaser">${article.teaser}</span>
<br/>                    
                    
</c:forEach>

<form method = "post" action = "addarticle">
<input type = "text" name= "title" value=""/><br/>
<input type = "text" name= "teaser" value=""/><br/>
<textarea name= "body" ></textarea>

<input type = "submit" value="Add Article"/>
</form>

</body>
</html>