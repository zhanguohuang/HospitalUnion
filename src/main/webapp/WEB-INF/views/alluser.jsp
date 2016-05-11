<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		padding:3%;
		border:2px dashed yellow;
		width: 20%;
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
</style>
</head>	
<body>
	
	<div id="rightbanner" >
		<h2 id="rightbanner_h2" >这里是标题</h2>
		<p id="rightbanner_p" >这里是正文</p>
		
		
	  <table width="500" border="0" cellspacing="0" cellpadding="0">
	    <tr>
		  <td>
		    <table width="500" >
				<form name="loginForm" method="post" action="">
				  <tr>
				    <td width="200"><div align="right">User Name:&nbsp;</div></td>
					<td width="200"><input type="text" name="username" value="" ></td>
				  </tr>
				  <tr>
				    <td width="200"><div align="right">Password:&nbsp;</div></td>
					<td width="200"><input type="password" name="password"></td>
				  </tr>
				  <tr>
				  	<td><span><c:out value="#{ message }" /></span></td>
				  </tr>
				  <tr>
					<td width="200"><br><input type="submit" name="submit" value="提交"></td>
				    <td width="200"><input type="reset" name="reset"></td>
				  </tr>
				  
				</form>
			</table>
		  </td>
		</tr>
	  </table>
	</div>
	
	<c:forEach items="${userList}" var="userList">
		<table>
			<tr>
				<td width="150px">${userList.id}</td>
				<td width="150px">${userList.username}</td>
				<td width="150px">${userList.password}</td>
			</tr>
		</table>
	</c:forEach>
	<a href="<%=request.getContextPath()%>">返回首页</a>
</body>
</html>