package com.KoreaIT.java.Jsp_AM.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.KoreaIT.java.Jsp_AM.util.DBUtil;
import com.KoreaIT.java.Jsp_AM.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/member/dojoin")
public class MemberDojoinServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// DB연결
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("클래스가 없습니다.");
			e.printStackTrace();
		}

		String url = "jdbc:mysql://127.0.0.1:3306/JSP_AM?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
		String user = "root";
		String password = "";

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, password);

//			int getid = Integer.parseInt(request.getParameter("Id"));
			
			String loginId = request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");
			String name = request.getParameter("name");
			
			SecSql sql = SecSql.from("SELECT *");
			sql.append("FROM `member`");
			sql.append("WHERE loginId = ?;", loginId);
			
			Map<String, Object> articleRow = DBUtil.selectRow(conn, sql);
			String a = (String) articleRow.get("loginId");
			
			if (a == null) {
				a = "-1";
			} else if (loginId == "") {
				response.getWriter()
				.append(String.format("<script>alert('아이디를 입력해주세요.'); location.replace('join');</script>"));
			} else if (loginPw == "") {
				response.getWriter()
				.append(String.format("<script>alert('비밀번호를 입력해주세요.'); location.replace('join');</script>"));
			} else if (name == "") {
				response.getWriter()
				.append(String.format("<script>alert('이름을 입력해주세요.'); location.replace('join');</script>"));
			} else if (a.equals(loginId)) {
				response.getWriter()
				.append(String.format("<script>alert('중복된 아이디 입니다.'); location.replace('join');</script>"));
			}
			
			sql = SecSql.from("INSERT INTO `member`");
			sql.append("SET regDate = NOW(),");
			sql.append("loginId = ?,", loginId);
			sql.append("loginPw = ?,", loginPw);
			sql.append("`name` = ?;", name);
			
			int id = DBUtil.insert(conn, sql);
			
			response.getWriter()
					.append(String.format("<script>alert('%d번 회원이 생성되었습니다.'); location.replace('join');</script>", id));
		
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}