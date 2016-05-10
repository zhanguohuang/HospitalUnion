<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8"/>
<title>Insert title here</title>
<style>
	td{
		border-style: solid ;
	}
</style>
</head>	
<body>
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