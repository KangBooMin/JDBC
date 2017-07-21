package com.javaex.jdbc.author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorUpdate {

	public static void main(String[] args) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null; // 쿼리와 관련된 일만 하는 pstmt

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공");

			// 3. SQL문 준비 / 바인딩(변수 '?'를 잡아주는) / 실행
			String query = "update author set author_name = '이 문 열', author_desc = '경상북도 영양' where author_id = 1";
			pstmt = conn.prepareStatement(query);

			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 처리");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패-" + e);
		} catch (SQLException e) {
			System.out.println("error-" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error-" + e);
			}

		}
	}

}
