package iot.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

@WebServlet(name = "loginproc", description = "로그인처리", urlPatterns = { "/loginproc" })
public class LoginProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn;
       
    public LoginProc() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Context initCtx = new InitialContext();
			Context envCtx  = (Context)initCtx.lookup("java:comp/env");
			DataSource ds   = (DataSource)envCtx.lookup("jdbc/java");
			conn		    = ds.getConnection();
			System.out.println("커넥션 풀로부터 DB 획득");			
			// 쿼리수행
			String uid 		= request.getParameter("uid");//"guest";
			String upw 		= request.getParameter("upw");//"1234";
			String sql 		= "select * from tbl_user where uid=? and upw=?;";
			PreparedStatement ps =  conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, upw);
			ResultSet rs    = ps.executeQuery();
			// Model
			User userBean = null;
			if( rs.next() ){
				userBean = new User();
				userBean.setIdx( 		rs.getInt("idx") );
				userBean.setUid( 		rs.getString("uid") );
				userBean.setUpw( 		rs.getString("upw") );
				userBean.setRegdate(	rs.getString("regdate") );
			}
			rs.close();
			ps.close();
			// 연결 종료
			conn.close();
			System.out.println("커넥션 풀에 DB 반납 했음 ");
			
			if( userBean == null ){		
				RequestDispatcher rd = request.getRequestDispatcher("loginError.jsp");
				rd.forward(request, response);
			}else{
				request.setAttribute("user", userBean);
				//RequestDispatcher rd = request.getRequestDispatcher("loginOk.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("/bbsProc");
				rd.forward(request, response);	
			}
			
		} catch (Exception e) {
			System.out.println("커넥션 풀 사용중 에러 발생 " + e.getMessage());
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
