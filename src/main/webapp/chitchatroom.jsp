<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
<title>Welcome come to here!</title>
</head>
<script>
	function addtest(){
		var div = $("#window");
		var input = $("<input></input>");
		input.attr("type","text");
		input.attr("value","8");
		div.append(input);
		alert($("#window").scrollHeight);
		div.scrollTop(div.scrollHeight);
	}
</script>

<body>
<div class="container">
	<div id="window" style="width:200px;height:100px;overflow-x:auto;overflow-y:auto;">
		<input type="text" value="1"/>
		<input type="text" value="2"/>
		<input type="text" value="3"/>
		<input type="text" value="4"/>
		<input type="text" value="5"/>
		<input type="text" value="6"/>
		<input type="text" value="7"/>
	</div>
	<br/>
	<textarea rows="3" cols="5"></textarea>
	<input type="button" onclick="addtest();" />
</div>	
</body>
</html>