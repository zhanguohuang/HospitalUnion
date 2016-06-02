<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
<title>Welcome come to here!</title>
<script>
	$(document).ready(function(){
		
	})
</script>
</head>
<body>
<div class="container">
	<div id="window" style="width:400px;height:100px;overflow-x:auto;overflow-y:auto;">
		<c:forEach items="${chatinfolist}" var="chatinfo">
			${chatinfo.username}:${chatinfo.message}<br/>
		</c:forEach> 
	</div>
	<br/>
	<form action="addmessage" method="post">
		<input type="text" value="${maxcreatetime}" />
		<input type="text" value="${username}"/><br/>
		内容：<textarea name="message" rows="3" cols="10"></textarea><br/>
	</form>
	
</div>	
</body>
</html>