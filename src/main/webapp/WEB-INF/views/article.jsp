<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored = "false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Article Page</title>
 <script src="<c:url value='resources/js/tinymce/tinymce.min.js'/>" ></script>
<link href="<c:url value='resources/css/Article.css' />" rel="stylesheet"></link>
<script>
tinymce.init({
	  selector: 'textarea',
	  height: 500,
	  theme: 'modern',
	  plugins: [
	    'advlist autolink lists link image charmap print preview hr anchor pagebreak',
	    'searchreplace wordcount visualblocks visualchars code fullscreen',
	    'insertdatetime media nonbreaking save table contextmenu directionality',
	    'emoticons template paste textcolor colorpicker textpattern imagetools'
	  ],
	  toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
	  toolbar2: 'print preview media | forecolor backcolor emoticons',
	  image_advtab: true,
	  templates: [
	    { title: 'Test template 1', content: 'Test 1' },
	    { title: 'Test template 2', content: 'Test 2' }
	  ],
	  content_css: [
	    '//fast.fonts.net/cssapi/e6dc9b99-64fe-4292-ad98-6974f93cd2a2.css',
	    '//www.tinymce.com/css/codepen.min.css'
	  ]
	 });
</script>
</head>
<body>

<c:forEach var="article" items="${listArticle}" varStatus="status">
               
                    <span class="title">${article.id}</span>
                    <span class="title"><a target="blank" href="articledetails?articleid=${article.id}">${article.title}</a></span>
                    <span class="teaser">${article.teaser}</span>
<br/>                    
                    
</c:forEach>

<form method = "post" action = "addarticle">
Article Title &nbsp;&nbsp;<input type = "text" name= "title" value=""/><br/>
Article Teaser <input type = "text" name= "teaser" value=""/><br/>
<textarea name= "body" ></textarea>

<input type = "submit" value="Add Article"/>
</form>

</body>
</html>