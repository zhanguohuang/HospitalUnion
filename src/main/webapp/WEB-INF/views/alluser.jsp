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
			//已经调通
			//var url = "ajax";
			//var args = {"id" : "15",username:"2",password:"13"};
			//$.getJSON(url,args,function(data){
			//	alert(data.password);
			//})
			
			var url = "ajax";
			var args = {"id" : "15",username:"2",password:"13"};
			$.ajax({
				type:"post",
				url:url,
				data:{"id" : "15","username":"2","password":"13"},
				success:function(data){
					alert(data);
				},
				dataType="json"
			})
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
	<c:forEach items="${userList}" var="userList">
		<table>
			<tr>
				<td width="150px">${userList.id}</td>
				<td width="150px">${userList.username}</td>
				<td width="150px">${userList.password}</td>
				<td width="150px"><a href="">删除</a></td>
			</tr>
		</table>
	</c:forEach>
	<a href="<%=request.getContextPath()%>">返回首页</a>
	<p id="click">点击测试ajax</p>
</body>
</html>