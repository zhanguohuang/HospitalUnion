<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<script type="text/javascript">
	$(document).ready(function(){
		var pagetotal = $("#pagetotal").val();
		var pagesize = $("#pagesize").val();
		var pagesum = Math.ceil(pagetotal/pagesize);
		for(var i=1;i<=pagesum;i++){
			var a = $("<a></a>");
			a.attr("href","alluser?currentpage="+i+"&pagesize="+pagesize);
			a.html(i);
			a.css("margin-right","10px");
			$("#paging").before(a);
		}
	})	
	
	$(document).ready(function(){
		$("#click").click(function(){
			var url = "alluser";
			$.getJSON(url,function(data){
				alert(data.userList[3].password);	
			});
		})
		$("#ajaxadd").click(function(){
			//var id = $("#id").val();
			//var username = $("#username").val();
			//var password = $("#password").val();
			//alert(id+';'+username+';'+password);
			
			//已经调通,简单的获取参数
			//var url = "ajax";
			//var args = {"id" : "15",username:"2",password:"13","email":"123@qq.com"};
			//$.getJSON(url,args,function(data){
			//	alert(data.password);
			//})
			
			//增加一个自定义的User,成功后重定向到alluser
			var url = "ajaxAddUser";
			var id = $("#id").val();
			var username = $("#username").val();
			var password = $("#password").val();
			var email = $("#email").val();
			var args = {"id":id, "username":username,"password":password,"email":email};
			$.ajax({
				type:"post",
				url:url,
				dataType:"json",
				contentType:"application/json",
				data:JSON.stringify(args),
				success:function(){
					window.location.href="alluser";
				}
			});
			
			//已经调通，增加多个User
			//var url = "ajaxAddUsers";
			//var saveDataAry = [];
			//var args1 = {"id" : "21","username":"theone","password":"21","email":"123@qq.com"};
			//var args2 = {"id" : "22","username":"theone","password":"22","email":"123@qq.com"};
			//saveDataAry.push(args1);
			//saveDataAry.push(args2);
			//$.ajax({
			//	type:"post",
			//	url:"ajaxAddUsers",
			//	dataType:"json",
			//	contentType:"application/json",
			//	data:JSON.stringify(saveDataAry),
			//	success:function(data){
			//		alert(data.id);
			//	}
			//});
		})
	})
	
	function checkemail(){
		var email = $("#email").val();
		var regex  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if(!regex.test(email)){
			alert("输入的邮箱格式不对，请重新输入");
		}
	}
</script>	
<body>	
<div class="container" >	
	<div class="col-xs-8">
	<div id="query">
		<form class="form-inline" role="form">
			用户id: <input type="text" name="qry_id" id="qry_id" value="${qry_id}" class="form-control" />&nbsp;
			用户名称: <input type="text" name="qry_username" id="qry_username" value="${qry_username}" class="form-control" />&nbsp;<br/>
			用户邮箱: <input type="text" name="qry_email" id="qry_email" value="${qry_email}" class="form-control" />&nbsp;
			<input type="submit" value="查询" class="btn btn-default" />		
				<table class="table table-striped table-bordered table-hover table-condensed">
					<caption>显示用户</caption>
					<thead>
						<tr>
							<th>用户ID</th>
							<th>用户名称</th>
							<th>用户密码</th>
							<th>用户邮箱</th>
							<th colspan="3">操作</th>
						</tr>
					</thead>
				<c:forEach items="${userList}" var="userList">
					<tbody>
						<tr>
							<td width="150px">${userList.id}</td>
							<td width="150px">${userList.username}</td>
							<td width="150px">${userList.password}</td>
							<td width="150px">${userList.email}</td>
							<td><a href="updateuser?id=${userList.id}" title="可修改任意字段">修改</a>&nbsp;</td>
							<td><a href="deleteuser?id=${userList.id}">删除</a>&nbsp;</td>
							<td><a href="email?id=${userList.id}">发邮件</a>&nbsp;</td>
						</tr>
					</tbody>
				</c:forEach>	
				</table>

			<div id="paging">	
				每页显示<input type="text" id="pagesize" name="pagesize" value="${pagesize}">条&nbsp;
				总共有<input type="button" id="pagetotal" value="${pagetotal}" class="btn btn-default"/>条数据&nbsp;
			</div>
		</form> 
	</div>
	
	<a href="export?qry_id=${qry_id}&qry_username=${qry_username}" class="btn btn-default">导出Excel</a><br/>
	<a href="<%=request.getContextPath()%>" class="btn btn-default">返回首页</a>
	<p id="click" class="btn btn-default">点击测试ajax</p>
	</div>
	<div class="col-xs-4">
	<h2>新增User</h2>
		<form method="post" action="/HospitalUnion/adduser">
		    <table class="table table-striped table-bordered table-hover table-condensed">			
				  <tr>	
				    <th width="120"><div align="right">ID:&nbsp;</div></th>
					<td width="120"><input type="text" name="id" id="id"></td>
				  </tr>
				  <tr>
				    <th width="120"><div align="right">User Name:&nbsp;</div></th>
					<td width="120"><input type="text" name="username" id="username"></td>
				  </tr>
				  <tr>
				    <th width="120"><div align="right">Password:&nbsp;</div></th>
					<td width="120"><input type="password" name="password" id="password"></td>
				  </tr>		
				  <tr>
				    <th width="120"><div align="right">Email:&nbsp;</div></th>
					<td width="120"><input type="text" name="email" id="email" onblur="checkemail()"></td>
				  </tr>	
				  <tr class="danger" >					
						<td colspan="2" style="padding:0px">${message}</td>
				  </tr>	
				  <tr>
				  	<td align="right"><input type="submit" value="新增" class="btn btn-default" /></td>
					<td><input type="button" id="ajaxadd" value="使用ajax增加user" class="btn btn-default" /></td>
				  </tr>
			</table>
	  </form>
	</div>	
</div>	
</body>
</html>