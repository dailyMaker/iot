<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${ user.uid }�� �氪���ϴ�.<br>
	<!-- �Խù� ���� -->	
	<table border="1">
		<thead>
			<tr>
				<td>�۹�ȣ</td>
				<td>�ۼ���</td>
				<td>����</td>
				<td>����</td>
				<td>�����</td>
			</tr>
		</thead>
		<tbody>
<c:forEach var="board" items="${ bbs }">	
	<tr>
		<td>${ board.bno }</td>
		<td>${ board.writer }</td>
		<td>${ board.title }</td>
		<td>${ board.content }</td>
		<td>${ board.regdate }</td>
	</tr>
</c:forEach>
		</tbody>
	</table>
</body>
</html>