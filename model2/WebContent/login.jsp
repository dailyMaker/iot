<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 화면의 비율 맞추는 -->
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<!-- 부트 스트랩 : 반응형웹  -->
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript">
/* $(document).ready(function(){
	$("form").submit(function(e){
		$.ajax({
			url:"/model2/loginproc",
			data:$("form").serialize(),
			dataType:"json",
			type:"get",
			success:function(json){
				console.log(json.uid, json.upw);
				document.location.href="bbs3.html";
			},
			error:function(err){
				alert("로그인 실패");
			}
		});
		e.preventDefault();
		return false;
	});	
}); */
</script>
</head>
<body>
	<fieldset>
		<form action="/model2/loginproc">
			<input type="text" name="uid">
			<input type="password" name="upw">
			<input type="submit" value="로그인">
		</form>
	</fieldset>
</body>
</html>