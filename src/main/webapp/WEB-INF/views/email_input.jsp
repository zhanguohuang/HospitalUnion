<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>发送邮件</title>
</head>
<body>
	
	<div align="center">
		<form action="sendemail" method="post">
		<div class="head">
			<label>你正在给<input type="text" name="username" value="${user.username}" /></label>发送邮件：
			<label>邮箱地址为：<input type="text" name="email" value="${user.email}" /></label>
		</div>
		<label>发送邮件</label>	
			<p>编辑邮件内容：</p>
			<textarea name="content" cols="50" rows="20" ></textarea><br>
			<input type="submit" value="发送"  />
			<input type="reset" value="重置" />
		</form>
	</div>
</body>
</html>