package ch20.oracle.sec07;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardUpdateExample {

	public static void main(String[] args) {
		Connection conn = null;

		try {
			// JDBC Driver 등록
			Class.forName("oracle.jdbc.OracleDriver");

			// 연결하기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "javaproject", "12345");

			// 매개변수화된 SQL문 작성
			String sql = new StringBuilder().append("UPDATE boards SET ").append("btitle=?, ").append("bcontent=?, ")
					.append("bfilename=?, ").append("bfiledata=? ").append("WHERE bno=?").toString();
			// UPDATE boards SET btitle=?, bcontent=?, bfilename=?, bfiledata=? WHERE bno=?

			// PreparedStatement 얻기 및 저장 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, "눈사람");
			pstmt.setString(2, "눈으로 만든 사람");
			pstmt.setString(3, "snowman.jpg");
			pstmt.setBlob(4, new FileInputStream("src/ch20/oracle/sec07/snowman.jpg"));
			pstmt.setInt(5, 3); // boards 테이블에 있는 게시물 번호(bno) 생성
			// UPDATE boards SET btitle='눈사람', bcontent=?, bfilename=?, bfiledata=? WHERE
			// bno=?

			// SQL 문 실행
			int rows = pstmt.executeUpdate();
			System.out.println("수정된 행 수: " + rows);

			// PreparedStatement닫기
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					// 연결 끊기
					conn.close();
				} catch (SQLException e) {
				}
			}

		}
	}

}
