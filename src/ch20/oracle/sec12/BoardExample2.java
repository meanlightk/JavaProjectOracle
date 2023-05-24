package ch20.oracle.sec12;

import java.util.Scanner;

public class BoardExample2 {

	// Field => 키보드 입력을 받기 위한 Scanner 필드 추가
	private Scanner scanner = new Scanner(System.in);

	// Constructor

	// Method => 키보드 입력을 받기 위해 nextLine() 메소드 호출, 선택 메뉴에 다라 메소드로 호출
	public void list() {
		System.out.println();
		System.out.println("[게시물 목록]");
		System.out.println("------------------------------------------------------");
		System.out.printf("%-6s%-12s%-16s%-40s\n", "no", "writer", "date", "title");
		System.out.println("-------------------------------------------------------");
		System.out.printf("%-6s%-12s%-16s%-40s \n", "1", "writer", "2022.01.27", "게시판에 오신 것을 환영합니다.");
		System.out.printf("%-6s%-12s%-16s%-40s \n", "2", "writer", "2022.01.27", "올 겨울은 많이 춥습니다.");
		mainMenu();
	}

	public void mainMenu() {
		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println("메인메뉴 : 1.Create | 2.Read | 3.Clear | 4.Exit");
		System.out.print("메뉴 선택: ");
		String menuNo = scanner.nextLine();
		System.out.println();

		switch (menuNo) {
		case "1" -> create();
		case "2" -> read();
		case "3" -> clear();
		case "4" -> exit();
		}
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
