package iot.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model1.User;

@WebServlet(name = "joinProc", description = "회원가입", urlPatterns = { "/joinProc" })
public class JoinProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn;
    public JoinProc() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			Context initCtx = new InitialContext();
			Context envCtx  = (Context)initCtx.lookup("java:comp/env");
			DataSource ds   = (DataSource)envCtx.lookup("jdbc/java");
			conn		    = ds.getConnection();
			System.out.println("커넥션풀 연결완료");			
			// 쿼리문 수행
			String uid 		= request.getParameter("uid");//"guest";
			String upw 		= request.getParameter("upw");//"1234";
			String sql 		= "insert into tbl_user (uid, upw) values (?,?)";
			PreparedStatement ps =  conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, upw);
			
			int count = ps.executeUpdate();
			if( count > 0 ){
				request.setAttribute("code", "1");
				User userBean = null;
				userBean = new User();
				userBean.setUid(uid);
				userBean.setUpw(upw);
				request.setAttribute("user", userBean);
			}else{
				request.setAttribute("code", "0");
			}			
			// 연결종료
			conn.close();
			System.out.println("커넥션 풀 반납 완료 ");
			RequestDispatcher rd = request.getRequestDispatcher("joinOk.jsp");
			rd.forward(request, response);	
			
		} catch (Exception e) {
			System.out.println("커넥션풀 연결 오류 " + e.getMessage());
		}	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
	}

}

