<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="java.sql.*"
    import="model1.*"   
	 %>
<%!
Connection conn = null;
String url="jdbc:mysql://localhost:3306/iotdb";
String id="iotuser";
String pw="iotpw";
public void jspInit(){
	System.out.println("멤버변수 초기화 : DB 연결");
	try {
		Class.forName("com.mysql.jdbc.Driver");
		// 2단계 연결
		conn = DriverManager.getConnection(url, id, pw);
		System.out.println("연결완료");
	}catch(Exception e) {
		e.printStackTrace();
		System.out.println("연결실패");
		
	}
}

public void jspDestory(){
	try{
		conn.close();
		System.out.println("연결종료");
	}catch(Exception e){
		System.out.println("연결실패");
	}
	System.out.println("멤버변수 해제 : DB 연결 종료");
}

%>
<%
	String uid = "guest";
	String upw = "1234";
	String sql = "select * from tbl_user where uid ='" + uid + "' and upw ='" + upw + "';";	
	//쿼리수행
	System.out.println("쿼리수행");
	Statement stmt = conn.createStatement();
	ResultSet rs   = stmt.executeQuery( sql );
	
	User userBean = null;
	if(rs.next()){
		userBean = new User();
		userBean.setIdx(		rs.getInt("idx"));
		userBean.setUid(		rs.getString("uid"));
		userBean.setUpw(		rs.getString("upw"));
		userBean.setRegdate(	rs.getString("regdate"));
	}
	rs.close();
	stmt.close();
	
	if( userBean == null ){		
		RequestDispatcher rd = request.getRequestDispatcher("loginError.jsp");
		rd.forward(request, response);
	}else{
		request.setAttribute("user", userBean);
		RequestDispatcher rd = request.getRequestDispatcher("bbs.jsp");
		rd.forward(request, response);	
	}	
%>
