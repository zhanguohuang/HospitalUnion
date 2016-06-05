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
					var username = data[i].username;
					var create_time = data[i].create_time;
					var message = data[i].message;
					var image_url = data[i].image_url;
					insertleftside(username,message,create_time,image_url);
				};
				if(data.length > 0){
					scrollToButtom();
					$("#lasttime").val(data[data.length-1].create_time);
				}			
			},
			error:function(){
			}
		});
	}
	
	function insertleftside(username,message,create_time,image_url){
		var content = $("#content");
		var div = $("<div style='float:left'><img src="+image_url+" class='img-circle' height='50' width='50'></div>");
		content.append(div);
		div = $("<div style='height:5;float:left;margin-left:5px'><label class='text-muted'>"+create_time+"</label></div><br/>");
		content.append(div);
		div = $("<div style='float:left;margin-left:5px'><input type='button' class='btn btn-primary' value="+message+"></div><br/><br/>");
		content.append(div);
	}

	function sendMessage(){
		var username =  $("#username").val();
		var message = $("#message").val();
		var image_url = $("#image_url").val();
		var url = "ajaxaddchatinfo";
		var args = {"username" : username,"message":message};
		$.ajax({
			type:"post",
			url:url,
			dataType:"json",
			contentType:"application/json",
			data:JSON.stringify(args),
		});
		insertrightside(username,message,image_url);		
		scrollToButtom();
		$("#message").val(null);
	}
	
	function insertrightside(username,message,image_url){
		var content = $("#content");
		var create_time = getDate();
		var div = $("<div style='float:right'><img src="+image_url+" class='img-circle' height='50' width='50'></div>");
		content.append(div);
		div = $("<div style='height:5;float:right;margin-right:5px'><label class='text-muted'>"+create_time+"</label></div><br/>");
		content.append(div);
		div = $("<div style='float:right;margin-right:5px'><input type='button' class='btn btn-success' value="+message+"></div><br/><br/>");
		content.append(div);
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
		//alert(window.scrollTop());后期给钱就优化的地方，可控制滚动条是否自由滚动
	}
</script>
</head>
<body>
<div class="container">
	<div id="window" style="height:400;overflow-x:auto;overflow-y:auto;" class="col-xs-10 btn btn-default">
		<div id="content" >
		<c:forEach items="${chatinfolist}" var="chatinfo">
			<c:if test="${chatinfo.username ne username}">
				<div style="float:left"><img src="${chatinfo.image_url}" class="img-circle" height="50" width="50"></div>
				<div style="height:5;float:left;margin-left:5px"><label class="text-muted">${chatinfo.create_time}</label></div><br/>
				<div style="float:left;margin-left:5px"><input type="button" class="btn btn-primary" value="${chatinfo.message}"></div>
				<br/><br/>
			</c:if>	
			
			<div  class="me" >		
				<img src="${chatinfo.image_url}" class="img-circle" height="50" width="50">
				<div class="sentense">
				      <label class="text-muted time ">${chatinfo.create_time}</label>
				      <span>${chatinfo.message}</span>
				 </div>
			</div>
			
			<c:if test="${chatinfo.username eq username}">
				<div style="float:right"><img src="${chatinfo.image_url}" class="img-circle" height="50" width="50"></div>
				<div style="height:5;float:right;margin-right:5px"><label class="text-muted">${chatinfo.create_time}</label></div><br/>
				<div style="float:right;margin-right:5px"><input type="button" class="btn btn-success" value="${chatinfo.message}"></div>
				<br/><br/>
			</c:if>	
		</c:forEach>
		</div>
	</div>

	
	<div class="col-xs-10" style="margin-top:30px">
		<textarea name="message" id="message" class="form-control" rows="3"></textarea>
		<div class="col-xs-2 col-md-push-11" style="margin-top:20px">
			<input type="submit" class="btn btn-info" onclick="sendMessage()" value="发送">
		</div>
	</div>

</div>	
<input type="hidden" id="lasttime" value="${lasttime}" />
<input type="hidden" id="username" value="${username}" />
<input type="hidden" id="image_url" value="${image_url}" />
</body>
</html>
<script>
	$(document).ready(function(){
		scrollToButtom();
		self.setInterval("getCurrentChatinfo()",500)
	})
	
	$(document).keypress(function(key) {  
		if(key.which == 13)  
			sendMessage(); 
	}); 
</script>