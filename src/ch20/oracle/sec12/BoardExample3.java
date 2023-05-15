package ch20.oracle.sec12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import ch20.oracle.sec09.exam02.Board;

public class BoardExample3 {

	private Scanner scanner = new Scanner(System.in);
	private Connection conn;

	// Constructor
	public BoardExample3() {
		try {
			// JDBC Driver 등록
			Class.forName("oracle.jdbc.OracleDriver");

			// 연결하기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "javaproject", "12345");

		} catch (Exception e) {
			e.printStackTrace();
			exit();
		}
	}

	// Method
	public void list() {
		// 타이틀 및 컬럼명 출력
		System.out.println();
		System.out.println("[게시물 목록]");
		System.out.println("-----------------------------------");
		System.out.printf("%-6s%-12s%-16s%-40s\n", "no", "writer", "date", "title");
		System.out.println("-----------------------------------");

		// boards 테이블에서 게시물 정보를 가져와서 출력하기
		try {
			String sql = "" + "SELECT bno, btitle, bcontent, bwriter, bdate " + "FROM boards " + "ORDER BY bno DESC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// 데이터 행을 읽고 Board 객체 생성
				Board board = new Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				System.out.printf("%-6s%-12s%-16s%-40s \n", board.getBno(), board.getBwriter(), board.getBdate(),
						board.getBtitle());
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			exit();
		}

		// 메인 메뉴 출력
		mainMenu();
	}

// 이하 동일

	public void mainMenu() {
		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println("메인메뉴 : 1.Create | 2.Read | 3.Clear | 4.Exit");
		System.out.print("메뉴 선택: ");
		System.out.println();
	}

	public void create() {
		System.out.println("*** create() 메소드 실행됨");
		list();
	}

	public void read() {
		System.out.println("*** read() 메소드 실행됨");
		list();
	}

	public void clear() {
		System.out.println("*** clear() 메소드 실행됨");
		list();
	}

	public void exit() {
		System.exit(0);
	}

	public static void main(String[] args) {
		BoardExample2 boardExample = new BoardExample2();
		boardExample.list();
	}
}
