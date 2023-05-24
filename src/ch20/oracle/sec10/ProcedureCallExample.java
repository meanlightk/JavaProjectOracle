package ch20.oracle.sec10;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
// 프로시저 호출
public class ProcedureCallExample {

	public static void main(String[] args) {

		Connection conn = null;

		try {
			// JDBC Driver 등록
			Class.forName("oracle.jdbc.OracleDriver");

			// 연결하기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "javaproject", "12345");

			// 매개변수화된 호출문 작성과 CallableStatement 얻기
			String sql = "{call user_create(?,?,?,?,?,?)}";
			CallableStatement cstmt = conn.prepareCall(sql);

			// ? 값 지정 및 리턴 타입 지정
			cstmt.setString(1, "summer");	// 프로시저의 첫 번째 매개값
			cstmt.setString(2, "한여름");		// 프로시저의 두 번째 매개값
			cstmt.setString(3, "12345");	
			cstmt.setInt(4, 26);
			cstmt.setString(5, "summer@mycompany.com");
			cstmt.registerOutParameter(6, Types.INTEGER); // 여섯 째 ?는 OUT값(리턴값)임을 지정

			// 프로시저 실행 및 리턴값 얻기
			cstmt.execute();
			int rows = cstmt.getInt(6);
			System.out.println("저장된 행 수: " + rows);

			// CallableStatement 닫기
			cstmt.close();
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
