<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.sql.*"
    import="model1.*" %>
<%!
Connection conn = null;
String url = "jdbc:mysql://localhost:3306/iotdb";
String id  = "iotuser";
String pw  = "iotpw";
public void jspInit()
{
	System.out.println("맴버변수 초기화 : DB 연결");
	try {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, id, pw);
		System.out.println("연결완료");		
	} catch (Exception e) {
		System.out.println("연결실패");
	}
}
public void jspDestroy(){
	try{
		if( conn != null ){
			conn.close();
			System.out.println("연결 종료");
		}
	}catch(Exception e){
		System.out.println("연결 종료 실패");
	}
	System.out.println("맴버변수 해제 : DB 연결종료");
}
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물보기</title>
</head>
<body>
${ user.uid }님 반갑습니다.<br>
	<table border="1">
		<thead>
			<tr>
				<td>글번호</td>
				<td>작성자</td>
				<td>제목</td>
				<td>내용</td>
				<td>등록일</td>
			</tr>
		</thead>
		<tbody>
<%
	// 아이디, 비번을 이용하여 쿼리 수행
	String sql = "select * from tbl_board order by bno desc;";
	// 쿼리를 수행 코드
	Statement stmt = conn.createStatement();
	ResultSet rs   = stmt.executeQuery( sql );
	//  쿼리 결과로 다음 데이터가 존재하면 수행해라
	while( rs.next() ){
%>
			<tr>
				<td><%= rs.getInt("bno") %></td>
				<td><%= rs.getString("writer") %></td>
				<td><%= rs.getString("title") %></td>
				<td><%= rs.getString("content") %></td>
				<td><%= rs.getString("regdate") %></td>
			</tr>
<%		
	}
	rs.close();
	stmt.close();
%>
		</tbody>
	</table>
</body>
</html>