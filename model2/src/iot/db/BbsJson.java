package iot.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;

import model1.Board;
import model1.ResBoard;

@WebServlet(name = "bbsJson", description = "게시판json", urlPatterns = { "/bbsJson" })
public class BbsJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn;
    public BbsJson() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Context initCtx = new InitialContext();
			Context envCtx  = (Context)initCtx.lookup("java:comp/env");
			DataSource ds   = (DataSource)envCtx.lookup("jdbc/java");
			conn		    = ds.getConnection();
			System.out.println("커넥션 풀 연결완료");			
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
			// 연결종료
			conn.close();
			System.out.println("커넥션풀 반환 성공 ");
			
			// 보드 응답
			ResBoard resData = new ResBoard();
			resData.setCode(1);
			resData.setBoards(boards);
			
			// GSON데이타로 가공
			Gson gson 	= new Gson();
			// Object -> String
			String data = gson.toJson(resData);
			
			// 응답처리
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=utf-8");
			response.getWriter().append(data);
			
		} catch (Exception e) {
			System.out.println("응답처리에 오류 발생 " + e.getMessage());
		}	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
