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
		var username = $("#username").val();
		var lasttime = $("#lasttime").val();
		var url = "getCurrentChatinfo";
		var args = {"username" : username,"create_time":lasttime};
		$.ajax({
			type:"post",
			url:url,
			dataType:"json",
			contentType:"application/json",
			data:JSON.stringify(args),
			success:function(data){
				for(var i=0;i<data.length;i++){
					var table = $("#messages");
					var tr = $("<tr></tr>");
					var td1 = $("<td></td>");
					var td2 = $("<td></td>");
					var username = data[i].username;
					var create_time = data[i].create_time;
					var message = data[i].message;
					td1.html(username+'('+create_time+'):');
					td2.html(message);
					tr.append(td1);
					tr.append(td2);
					table.append(tr);	
				};
				if(data.length > 0){
					scrollToButtom();
					$("#lasttime").val(data[data.length-1].create_time);
				}			
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
		});
		var table = $("#messages");
		var tr = $("<tr></tr>");
		var td1 = $("<td></td>");
		var td2 = $("<td></td>");
		var create_time = getDate();
		td1.html(username+'('+create_time+'):');
		td2.html(message);
		tr.append(td1);
		tr.append(td2);
		table.append(tr);
		scrollToButtom();
		$("#message").val(null);
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
	
	function getDate(){	
		var date = new Date();
		var year = date.getFullYear();
		var month = (date.getMonth()+1)>10?(date.getMonth()+1):'0'+(date.getMonth()+1);
		var day = date.getDate()>10?date.getDate():'0'+date.getDate();
		var hours = date.getHours()>10?date.getHours():'0'+date.getHours();
		var minutes = date.getMinutes()>10?date.getMinutes():'0'+date.getMinutes();
		var seconds = date.getSeconds()>10?date.getSeconds():'0'+date.getSecounds();
		var create_time = year+'-'+month+'-'+day+' '+hours+':'+minutes+':'+seconds+'.0';
		return create_time;
	}
	
	function scrollToButtom(){
		var window = $("#window");
		var content = $("#content");
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