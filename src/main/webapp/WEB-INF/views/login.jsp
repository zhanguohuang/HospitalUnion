<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
<title>此页面还未正式使用</title>
</head>
<body>
	<form action="/login" method="post">
		<dl>
			<dt>User:</dt>
			<dd><input type="text" name="username" /></dd>
		</dl>
		<dl>
			<dt>Password:</dt>
			<dd><input type="password" name="password" /></dd>
		</dl>
		<dl>
			<dt colspan="2"><input type="submit" value="Login"></dt>
		</dl>
	</form>
</body>
</html>