package iot.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model1.Board;

@WebServlet(name = "bbsProc", description = "게시판입력처리", urlPatterns = { "/bbsProc" })
public class BbsProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn;
    public BbsProc() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시판 입력처리
		try {
			Context initCtx = new InitialContext();
			Context envCtx  = (Context)initCtx.lookup("java:comp/env");
			DataSource ds   = (DataSource)envCtx.lookup("jdbc/java");
			conn		    = ds.getConnection();
			System.out.println("커넥션풀 획득");			
			// 쿼리문			
			String sql 		= "select * from tbl_board;";
			PreparedStatement ps =  conn.prepareStatement(sql);			
			ResultSet rs    = ps.executeQuery();
			// Model
			ArrayList<Board> boards = new ArrayList<Board>();
			Board boardBean = null;
			while( rs.next() ){
				boardBean = new Board();	
				boardBean.setBno( 		rs.getInt("bno") );
				boardBean.setTitle(		rs.getString("title"));
				boardBean.setContent(	rs.getString("content"));
				boardBean.setWriter(	rs.getString("writer"));
				boardBean.setRegdate(	rs.getString("regdate"));
				boards.add(boardBean);
			}
			rs.close();
			ps.close();
			// 커넥션 종료
			conn.close();
			System.out.println("커넥션풀 반납완료 ");
			
			request.setAttribute("bbs", boards);
			RequestDispatcher rd = request.getRequestDispatcher("bbs2.jsp");
			rd.forward(request, response);	
			
		} catch (Exception e) {
			System.out.println("커넥션 풀에서 오류발생" + e.getMessage());
		}	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
