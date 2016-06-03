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
		var window = $("#window");
		var content = $("#content");
		window.scrollTop(content.height());
		
	})
	
	function getCurrentChatinfo(){
		var username = $("#username");
		//var lasttime = $("#lasttime");
		var create_time = '2016-06-03 11:49:35';
		var message = $("#message").val();
		var url = "getCurrentChatinfo";
		var args = {"username" : username,"message":message};
		$.ajax({
			type:"post",
			url:url,
			dataType:"json",
			contentType:"application/json",
			data:JSON.stringify(args),
			success:function(){
			},
			error:function(){
				alert("error");
			}
		});
	}
	

	function sendMessage(){
		var username =  $("#username").val();
		var message = $("#message").val();
		
		var url = "ajaxaddchatinfo";
		var args = {"username" : username,"message":message};
		$.ajax({
			type:"post",
			url:url,
			dataType:"json",
			contentType:"application/json",
			data:JSON.stringify(args),
			success:function(){
			},
			error:function(){
				$("#message").val(null);
			}
		});
	}
	
	function　addinput(){
		var window = $("#window");
		var content = $("#content");
		var input = $("<input></input>");
		input.attr("type","text");
		input.attr("value","新增的input");
		window.append(input);
		//alert(window.scrollTop());后期给钱就优化的地方，可控制滚动条是否自由滚动
		window.scrollTop(content.height());
	}
</script>
</head>
<body>
<div class="container">
	<div id="window" style="width:70%;height:100px;overflow-x:auto;overflow-y:auto;">
		<div id="content">
			<table id="messages">
				<c:forEach items="${chatinfolist}" var="chatinfo">
				<tr>
					<td>${chatinfo.username}(${chatinfo.create_time}):</td>
					<td>${chatinfo.message}</td>				
				</tr>				
				</c:forEach> 
			</table>
		</div>
	</div>
	<br/>
	<form action="addmessage" method="post">
		<input type="text" id="lasttime" name="lasttime" value="${lasttime}" />
		<input type="text" id="username" value="${username}"/><br/>
		内容：<textarea name="message" id="message" rows="3" cols="10"></textarea><br/>
		<input type="button" value="发送" onclick="sendMessage()" />
	</form>
	<input type="button" value="点击增加input" onclick="addinput()" />
	<input type="button" value="点击获取当前聊天" onclick="getCurrentChatinfo()" />
</div>	
</body>
</html>