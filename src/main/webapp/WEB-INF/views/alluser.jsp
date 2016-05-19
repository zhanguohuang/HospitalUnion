<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
<title>Insert title here</title>
<style>
	td{
		border-style:10px solid ;
	}
	div#rightbanner{
		/* 这里对整个右侧的float盒模型进行定义 */
		/* 盒模型 */
		/* padding:3%; */
		border:2px dashed black;
		/* width: 5%; */
		/* 浮动 */
		float:right;
		/* 背景颜色 */
		background-color: write;
		/* 字体大小，居中，行距 */
		font-size: 1.2em;
		text-align: center;
		line-height: 1.4em;
		/* 字体颜色  */
		color: black;
	}
	div#rightbanner_h2{
		/* 这里对右侧的标题h2进行定义 */
		
	}
	div#rightbanner_p{
		/* 这里对右侧的正文p进行定义 */
	}
	#msg{
		color: red;
	}
</style>
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
			//var args = {"id" : "15",username:"2",password:"13"};
			//$.getJSON(url,args,function(data){
			//	alert(data.password);
			//})
			
			//增加一个自定义的User,成功后重定向到alluser
			var url = "ajaxAddUser";
			var id = $("#id").val();
			var username = $("#username").val();
			var password = $("#password").val();
			var args = {"id":id, "username":username,"password":password};
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
			//var args1 = {"id" : "21","username":"theone","password":"21"};
			//var args2 = {"id" : "22","username":"theone","password":"22"};
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
</script>	
<body>
	
	<div id="rightbanner" >	
	<h2 id="rightbanner_h2" >新增User</h2>
	<form method="post" action="/HospitalUnion/adduser">
		    <table>			
				  <tr>	
				    <td width="120"><div align="right">ID:&nbsp;</div></td>
					<td width="120"><input type="text" name="id" id="id"></td>
				  </tr>
				  <tr>
				    <td width="120"><div align="right">User Name:&nbsp;</div></td>
					<td width="120"><input type="text" name="username" id="username"></td>
				  </tr>
				  <tr>
				    <td width="120"><div align="right">Password:&nbsp;</div></td>
					<td width="120"><input type="password" name="password" id="password"></td>
				  </tr>			
			</table>
			<c:if test="${!message}">
				  <div id="msg"><c:out value="${message}" /></div>
			</c:if>
				<input type="submit"value="新增"/>
				<input type="button" id="ajaxadd" value="使用ajax增加user"/>
				<input type="reset"/>
	  </form>
	</div>
	
	<div id="query">
		<form>
			用户id: <input type="text" name="qry_id" id="qry_id" value="${qry_id}" />&nbsp;
			用户名称: <input type="text" name="qry_username" id="qry_username" value="${qry_username}" />&nbsp;
			<input type="submit" value="查询" />
			<c:forEach items="${userList}" var="userList">
				<table>
					<tr>
						<td width="150px">${userList.id}</td>
						<td width="150px">${userList.username}</td>
						<td width="150px">${userList.password}</td>
						<td><a href="updateuser?id=${userList.id}" title="可修改任意字段">修改</a></td>
						<td width="150px"><a href="deleteuser?id=${userList.id}">删除</a></td>
					</tr>
				</table>
			</c:forEach>
			<div id="paging">	
				每页显示<input type="text" id="pagesize" name="pagesize" value="${pagesize}">条&nbsp;
				总共有<input type="button" id="pagetotal" value="${pagetotal}" />条数据&nbsp;
			</div>
		</form> 
	</div>
	<a href="export?qry_id=${qry_id}&qry_username=${qry_username}">导出Excel</a><br/>
	<a href="<%=request.getContextPath()%>">返回首页</a>
	<p id="click">点击测试ajax</p>
</body>
</html>