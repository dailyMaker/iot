<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="java.sql.*"
    import="model1.*"   
    import="iot.db.*"   
 %>
 <%
	String uid = "guest";
	String upw = "1234";
	String sql = "select * from tbl_user where uid ='" + uid + "' and upw ='" + upw + "';";	
	//쿼리수행
	System.out.println("쿼리수행");
	Connection conn = ConnectionContext.getConnection();
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
