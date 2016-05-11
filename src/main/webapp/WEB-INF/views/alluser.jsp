<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8"/>
<title>Insert title here</title>
<style>
	td{
		border-style: solid ;
	}
	div#rightbanner{
		/* 这里对整个右侧的float盒模型进行定义 */
		/* 盒模型 */
		/* padding:3%; */
		border:2px dashed yellow;
		/* width: 5%; */
		/* 浮动 */
		float:right;
		/* 背景颜色 */
		background-color: lightcyan;
		/* 字体大小，居中，行距 */
		font-size: 1.2em;
		text-align: center;
		line-height: 1.4em;
		/* 字体颜色  */
		color: rgb(10, 255, 180);
	}
	div#rightbanner_h2{
		/* 这里对右侧的标题h2进行定义 */
		
	}
	div#rightbanner_p{
		/* 这里对右侧的正文p进行定义 */
	}
	tr#msg{
		background-color: pink;
		color: orange;
	}
</style>
</head>	
<body>
	
	<div id="rightbanner" >	
	<h2 id="rightbanner_h2" >新增User</h2>
	<form method="post" action="/HospitalUnion/adduser">
		    <table>			
				  <tr>	
				    <td width="120"><div align="right">ID:&nbsp;</div></td>
					<td width="120"><input type="text" name="id"></td>
				  </tr>
				  <tr>
				    <td width="120"><div align="right">User Name:&nbsp;</div></td>
					<td width="120"><input type="text" name="username"></td>
				  </tr>
				  <tr>
				    <td width="120"><div align="right">Password:&nbsp;</div></td>
					<td width="120"><input type="password" name="password"></td>
				  </tr>
				  <c:if test="${!message}">
					  <tr id="msg">
					  	<td colspan="2" ><c:out value="${message}" /></td>
					  </tr>
				  </c:if>
				  <tr>
					<td width="100"><div align="right"><input type="submit"value="新增"></div></td>
				    <td width="100"><div align="right"><input type="reset"></div></td>
				  </tr>				
			</table>
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
</body>
</html>