package min.java.Siksin.view;

import java.util.Scanner;

import min.java.Siksin.member.control.SiksinMemberDelete;
import min.java.Siksin.member.control.SiksinMemberInsert;
import min.java.Siksin.member.control.SiksinMemberSelect;
import min.java.Siksin.member.control.SiksinMemberUpdate;

public class SiksinMemberMain {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println();
			System.out.println(
					"------------------------------------------ <식신> ------------------------------------------");
			System.out.println(
					"------------------------------------ 대한민국 NO.1 맛집 서비스  -----------------------------------");
			System.out.println("[회원정보]");
			System.out.println("[1] 회원조회  | [2] 회원가입 | [3] 회원수정 | [4] 회원정보삭제 | [5] 회원정보 페이지 종료");
			System.out.print("◆ 원하는 번호를 입력하세요 : ");
			String choice = scanner.next();

			switch (choice) {
			case "1":
				SiksinMemberSelect siksinMemberSelect = new SiksinMemberSelect();
				siksinMemberSelect.execute(scanner);
				break;
			case "2":
				SiksinMemberInsert siksinMemberInsert = new SiksinMemberInsert();
				siksinMemberInsert.execute(scanner);
				break;
			case "3":
				SiksinMemberUpdate siksinMemberUpdate = new SiksinMemberUpdate();
				siksinMemberUpdate.execute(scanner);
				break;
			case "4":
				SiksinMemberDelete siksinMemberDelete = new SiksinMemberDelete();
				siksinMemberDelete.execute(scanner);
				break;
			case "5":
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			default:

				break;
			}

		} while (true);
	}
}
