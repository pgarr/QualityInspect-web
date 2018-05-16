package com.pgarr.qualityinspect.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String user = "qualityinspect";
		String pass = "qualityinspect";

		String jdbcUrl = "jdbc:mysql://localhost:3306/quality-inspect-web?useSSL=false";
		String driver = "com.mysql.jdbc.Driver";

		try {
			PrintWriter out = response.getWriter();

			out.println("Connecting to database: " + jdbcUrl);

			Class.forName(driver);

			Connection conn = DriverManager.getConnection(jdbcUrl, user, pass);

			out.println("\nSUCCESS!");

			conn.close();
		} catch (Exception exc) {
			exc.printStackTrace();
			throw new ServletException(exc);
		}
	}

}
