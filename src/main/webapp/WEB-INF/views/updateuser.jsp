<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
	<title>修改user</title>
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
<div class="container">
	<div class="center-block">
		<form action="" method="post" class="form-horizontal" role="form">
		<div class="form-group">
			<label class="col-xs-1 control-label">ID:</label>
			<div class="col-xs-2">
         		<input type="text" name="id" value="${user.id}" readonly class="form-control" >
      		</div>
		</div>
		<div class="form-group">
			<label class="col-xs-1 control-label">Username:</label>
			<div class="col-xs-2">
         		<input type="text" name="username" value="${user.username}" class="form-control" placeholder="请输入名字">
      		</div>
		</div>
		<div class="form-group">
			<label class="col-xs-1 control-label">Password:</label>
			<div class="col-xs-2">
         		<input type="password" name="password" value="${user.password}" class="form-control" placeholder="请输入密码">
      		</div>
		</div>
		<div class="form-group">
			<label class="col-xs-1 control-label">Email</label>
			<div class="col-xs-2">
         		<input type="text" name="email" id="email" value="${user.email}" onblur="checkemail()" class="form-control" placeholder="请输入邮箱">
      		</div>
		</div>
		<div class="form-group col-xs-3 col-md-push-1">
			<input type="submit" class="btn btn-default" value="确认修改">
		</div>	
		</form>
	</div>
</div>		
</body>
</html>