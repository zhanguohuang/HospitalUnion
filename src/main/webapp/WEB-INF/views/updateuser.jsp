<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<script type="text/javascript" src="js/jquery-1.12.3.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function(){
		alert("hello js");
	})
</script>
</head>
<body>
	<div align="center">
		需要修改的User为:<br/>
		<form action="" method="post">
		<table>
			<tr>
				<td>id :</td>
				<td><input type="text" name="id" value="${user.id}" readonly /></td>
			</tr>
			<tr>
				<td>username :</td>
				<td><input type="text" name="username" value="${user.username}" /></td>
			</tr>
			<tr>
				<td>password :</td>
				<td><input type="text" name="password" value="${user.password}" /></td>
			</tr>			
		</table>
			<input type="submit" value="确认修改">
		</form>
	</div>	
</body>
</html>