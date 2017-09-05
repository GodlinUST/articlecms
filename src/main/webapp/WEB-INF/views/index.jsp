<%@ page isELIgnored = "false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Articlecms</title>
<link href='resources/css/Article.css' rel="stylesheet"></link>
<link href='resources/css/pure-min.css' rel="stylesheet"></link>
</head>
<body>
<h2>${welcomemsg}</h2>
<form class="pure-form pure-form-stacked" action ="login" method="post">
UserId: <input type="text" name="userid" value=""><br/>
Password : <input type="password" name="pwd" value="">
<input type="submit" class="pure-button pure-button-primary" value="Login">
</form>

</body>
</html>
