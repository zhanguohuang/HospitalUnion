<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
	<title>Insert title here</title>
<script>
	function checkemail(){
		var email = $("#email").val();
		var regex  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if(!regex.test(email)){
			alert("输入的邮箱格式不对，请重新输入");
		}
	}
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
			<tr>
				<td>email :</td>
				<td><input type="text" name="email" id="email" value="${user.email}" onblur="checkemail()"/></td>
			</tr>		
		</table>
			<input type="submit" value="确认修改">
		</form>
	</div>	
</body>
</html>