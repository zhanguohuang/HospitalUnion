<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
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