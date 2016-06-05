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
	$(document).ready(function(){
		var  con_width= $("#content").outerHeight();
		if(con_width>400){
		$("#window").css({"overflow-x":"hidden" ,"overflow-y":"scroll"});
		}else{
		   $("#window").css("overflow-y","hidden"); 
		}		
	});
</script>
</head>
<body>
<div class="container">
	<div id="window"  class="col-xs-10">
		<div id="content"  class="fix">
			<div  class="others">
			      <img src="<%=request.getContextPath() %>/images/p1_img1.jpg" class="img-circle" height="50" width="50">
			      <div class="sentense">
			            <label class="text-muted time">2016-04-26</label>
			            <span>hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!</span>	      
			      </div>		
			</div>			
			<div  class="me" >		
					<img src="<%=request.getContextPath() %>/images/proimg4.jpg" class="img-circle" height="50" width="50">
					<div class="sentense">
					      <label class="text-muted time ">2016-04-26</label>
					      <span>hi!!hello!hellollo!hello!hello!hello!!hello!hellollo!hello!hello!hello!</span>
					 </div>
			</div>									
		</div>
	</div>
	
	<div class="col-xs-10" style="margin-top:30px">
		<textarea class="form-control" rows="3"></textarea>
		<div class="col-xs-2 col-md-push-11" style="margin-top:20px">
			<input type="submit" class="btn btn-info" onclick="sendMessage()" value="发送">
		</div>
	</div>

</div>	
</body>
</html>