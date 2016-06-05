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

<style>
#window{ border: 1px solid #ccc; padding: 15px; height:400px; margin-top:20px;}
#content{ position: relative; bottom: 0; left: 0;  }
.fix {*zoom: 1;}
.fix:after {display: table;content: '';clear: both;}
.others{ float: left; width:100%;}
.me{ float:right; width:100%}
.time{height:5;  font-size: 12px; width: 100%}
.sentense{width: 88%;}
.sentense span{text-align:left; display:inline-block; line-height: 20px; margin: 10px 0; border-radius:5px; color: #fff;word-wrap:break-word ;padding:7px; max-width: 801px;}

.others img ,.others .sentense{ float: left;}
.others  .sentense {margin-left: 5px}
.others  .sentense span{ background: #339933}

.me img , .me .sentense{ float: right;}
.me .sentense{ margin-right: 5px;}
.me  .sentense .time{ width: 100%;text-align: right;}
.me  .sentense span{  background: #0099CC; float: right; }
</style>

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
		var div = $("<div class='me'>"		
					+"<img src="+image_url+" class='img-circle' height='50' width='50'>"
					+"<div class='sentense'>"
		      		+"<label class='text-muted time'>"+create_time+"</label>"
		      		+"<span>"+message+"</span></div></div>");
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
		var div = $("<div class='me'>"		
					+"<img src="+image_url+" class='img-circle' height='50' width='50'>"
					+"<div class='sentense'>"
		      		+"<label class='text-muted time'>"+create_time+"</label>"
		      		+"<span>"+message+"</span></div></div>");
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
	<div id="window"  class="col-xs-10">
		<div id="content"  class="fix">
		<c:forEach items="${chatinfolist}" var="chatinfo">
			<c:if test="${chatinfo.username ne username}">
				<div  class="others" >		
					<img src="${chatinfo.image_url}" class="img-circle" height="50" width="50">
					<div class="sentense">
					      <label class="text-muted time ">${chatinfo.create_time}</label>
					      <span>${chatinfo.message}</span>
					</div>
				</div>
			</c:if>				
			<c:if test="${chatinfo.username eq username}">
				<div  class="me" >		
					<img src="${chatinfo.image_url}" class="img-circle" height="50" width="50">
					<div class="sentense">
					      <label class="text-muted time ">${chatinfo.create_time}</label>
					      <span>${chatinfo.message}</span>
					</div>
				</div>
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
	$(document).keypress(function(key) {  
		if(key.which == 13)  
			sendMessage(); 
	}); 
	$(document).ready(function(){
		var  con_width= $("#content").outerHeight();
		if(con_width>400){
		$("#window").css({"overflow-x":"hidden" ,"overflow-y":"scroll"});
		}else{
		   $("#window").css("overflow-y","hidden"); 
		}
		scrollToButtom();
		self.setInterval("getCurrentChatinfo()",500)
	})
	
	
</script>