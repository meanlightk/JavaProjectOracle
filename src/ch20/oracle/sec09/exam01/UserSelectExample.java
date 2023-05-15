package ch20.oracle.sec09.exam01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSelectExample {

	public static void main(String[] args) {
		Connection conn = null;

		try {
			// JDBC Driver 등록
			Class.forName("oracle.jdbc.OracleDriver");

			// 연결하기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "javaproject", "12345");

			// 매개변수화된 SQL문 작성
			String sql = "" + "SELECT userid, username, userpassword, userage, useremail " + "FROM users "
					+ "WHERE userid = ?";

			// PreparedStatement 얻기 및 저장 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "winter");

			// SQL 문 실행
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

				User user = new User();
				user.setUserId(rs.getString("userid"));
				user.setUserName(rs.getString("username"));
				user.setUserPassword(rs.getString("userpassword"));
				user.setUserAge(rs.getInt(4));	// 컬럼 순번을 이용
				user.setUserEmail(rs.getString(5));	// 컬럼 순번을 이용
				System.out.println("user");
			} else {		// 데이터 행을 가져오지 않았을 경우
				System.out.println("사용자 아이딕 존재하지 않음");
			}
			rs.close();

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
