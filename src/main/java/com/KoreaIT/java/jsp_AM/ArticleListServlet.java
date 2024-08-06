package com.KoreaIT.java.jsp_AM;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Article/list")
public class ArticleListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		// DB 연결
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					System.out.println("클래스 x");
					e.printStackTrace();
				}

		
		response.getWriter().append(" 123 ");

		String url = "jdbc:mysql://localhost:3306/ArticleManager";
		String user = "root";
		String password = "";

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, password);
			response.getWriter().append("DB 연결 성공!");
			
			
			DBUtil dbUtil = new DBUtil(request, response);

			String sql = "SELECT * FROM article";

			List<Map<String, Object>> articleRows = dbUtil.selectRows(conn, sql);

			
//			response.getWriter().append(articleRows.toString());

			// jsp 파일에서 articleRows를 쓸 수 있도록 에트리뷰트로 만듬.
			// jsp 파일에게 DB에서 가져온 내용을 넘겨주기 위해서 이다.
			request.setAttribute("articleRows", articleRows);
			request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
