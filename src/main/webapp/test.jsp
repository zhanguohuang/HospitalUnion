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
		//var length = $("#td").html().length;
		//var rowspan = length/10;
	})
</script>
</head>
<body>
<div class="container">
	<div id="window" style="height:400;overflow-x:auto;overflow-y:auto;" class="col-xs-10 btn btn-default">
		<div id="content" >
			<div style="float:left"><img src="${chatinfo.image_url}" class="img-circle" height="50" width="50"></div>
			<div style="height:5;float:left;margin-left:5px"><label class="text-muted">${chatinfo.create_time}</label></div><br/>
			<div style="float:left;margin-left:5px"><input type="button" class="btn btn-primary" value="${chatinfo.message}"></div>
			<br/><br/>
			<div style="float:right"><img src="${chatinfo.image_url}" class="img-circle" height="50" width="50"></div>
			<div style="height:5;float:right;margin-right:5px"><label class="text-muted">${chatinfo.create_time}</label></div><br/>
			<div style="float:right;margin-right:5px"><input type="button" class="btn btn-success" value="${chatinfo.message}"></div>
		</div>
	</div>


	<div class="col-xs-10" style="margin-top:30px">
		<textarea class="form-control" rows="3"></textarea>
		<div class="col-xs-2 col-md-push-11" style="margin-top:20px">
			<input type="submit" class="btn btn-info" onclick="sendMessage()" value="发送">
		</div>
	</div>

</div>	
</body>
</html>