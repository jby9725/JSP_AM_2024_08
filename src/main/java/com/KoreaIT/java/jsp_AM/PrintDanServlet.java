package com.KoreaIT.java.jsp_AM;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/printDan")
public class PrintDanServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		String input_dan = request.getParameter("dan");
		String input_limit = request.getParameter("limit");
		String input_color = request.getParameter("color");
		
		if(input_dan == null) {
			input_dan = "1";
		}
		
		if(input_limit == null) {
			input_limit = "1";
		}
		
		
		int dan = Integer.parseInt(input_dan);
		int limit = Integer.parseInt(input_limit);
		
		
		response.getWriter().append(String.format("<div style=\"color:%s;\" >== %d단 ==</div>", input_color, dan));
		
		for(int i = 1; i <= limit; i++) {
			response.getWriter().append(String.format("<div style=\"color:%s;\" >%d * %d = %d</div>",input_color, dan, i, dan * i));
		}
	}

}
