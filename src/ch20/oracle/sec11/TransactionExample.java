package ch20.oracle.sec11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 트랜잭션 -> 계좌이체
public class TransactionExample {

	public static void main(String[] args) {

		Connection conn = null;
		try {
			// JDBC Driver 등록
			Class.forName("oracle.jdbc.OracleDriver");

			// 연결하기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "javaproject", "12345");

			// -------------- 트랜잭션 시작 ---------------- //
			conn.setAutoCommit(false);
			/* 자동 커밋 기능 끄기 => UPDATE문 실행에 문제발생 : 출금 작업 후 바로 commit되기 떄문에
			성공 여부와 상관없이 출금 작업만 별도 처리 됨 */

			// 출금 작업
			String sql1 = "UPDATE accounts SET balance = balance-? WHERE ano=?";
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setInt(1, 10000);
			pstmt1.setString(2, "111-111-1111");
			int rows1 = pstmt1.executeUpdate();
			if (rows1 == 0)
				throw new Exception("출금되지 않았음");
			pstmt1.close();

			// 입금 작업
			String sql2 = "UPDATE accounts SET balance = balance+? WHERE ano=?";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, 10000);
			pstmt2.setString(2, "222-222-2222");
			/*pstmt2.setString(2, "333-333-3333");
			  입금계좌를 다음과 같이 다르게 주면, rows2가 0이 되므로 46 라인에서 예외가 발생하고,
			  예외처리 코드 59 라인에서 롤백됨.
			  롤백이 될 경우, 출금도 실패 처리되므로 출금 계좌와 입금 계좌의 금액은 변동되지 않음
			  */
			int rows2 = pstmt2.executeUpdate();
			if (rows2 == 0)
				throw new Exception("입금되지 않았음");
			pstmt2.close();

			// 수동 커밋 -> 모두 성공 처리
			conn.commit();
			System.out.println("계좌 이체 성공");
			// -------------- 트랜잭션 종료 ---------------- //
		} catch (Exception e) {
			e.printStackTrace();
			try {
				// 수동 롤백 -> 모두 실패 처리
				System.out.print("error");
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("계좌 이체 실패");
				e.printStackTrace();
			} finally {
				System.out.print("finally");
				if (conn != null) {
					try {
						System.out.print("restore autocommit");

						// 원래대로 자동 커밋 기능 켜기
						conn.setAutoCommit(true);
						// 연결 끊기
						conn.close();
					} catch (SQLException e2) {

					}
				}
			}

		}
	}
}
