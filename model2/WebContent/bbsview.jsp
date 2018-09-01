<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 화면의 비율 맞추는 -->
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#getdata").on('click', function(){
		showData();
	});
}); 

var showData=function(){
	$.ajax({
		url:"/model2/bbsJson",
		dataType:"json",
		type:"get",
		success:function(json){
			var data = json["boards"];
			var html="<table border=1>";
			html+="<tr><th>제목</th><th>내용</th><th>작성자</th></tr>";
			for(var i=0; i<data.length; i++){
				html+="<tr><td>"+data[i]["title"]+"</td><td>"+data[i]["content"]+"</td><td>"+data[i]["writer"]+"</td></tr>";
				//console.log (data[i]["title"])
				
			}
			html+="</table>";
			$('#board').html(html);
		},
		error:function(err){
			alert("실패");
		}
	});	
}

</script>
</head>
<body>
	<input type="button" id="getdata" value="게시물보기">
	<div id="board"></div>
</body>
</html>