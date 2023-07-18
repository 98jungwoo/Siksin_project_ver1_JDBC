package min.java.Siksin.member.control;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import min.java.Siksin.DAO.SiksinMemberDAO;
import min.java.Siksin.DTO.SiksinMemberDTO;
import min.java.Siksin.action.SiksinMemberAction;

public class SiksinMemberDelete implements SiksinMemberAction {
	private static final Log log = LogFactory.getLog(SiksinMemberDelete.class);

	@Override
	public void execute(Scanner scanner) {
		System.out.println();
		System.out.println("------ ◆ 회원정보 삭제을 선택하였습니다.◆ ------");
		SiksinMemberDAO siksinMemberDAO = new SiksinMemberDAO();
		ArrayList<SiksinMemberDTO> arrayList = new ArrayList<SiksinMemberDTO>();
		arrayList = siksinMemberDAO.SiksinMemberSelectAll();
		System.out.println("삭제할 이메일을 입력해주세요.");

		for (SiksinMemberDTO siksinMemberDTO : arrayList) {
			System.out.print("회원 이메일: ");
			String memberEmail = scanner.next();

			siksinMemberDTO = siksinMemberDAO.memberEmailcheck(memberEmail);

			if (siksinMemberDTO.getMemberEmail() == null) {
				System.out.println("삭제할 회원이 없습니다.");
			} else {
				log.info("Delete아이디 확인 - " + siksinMemberDTO.getMemberEmail().equals(memberEmail));
				System.out.println("삭제할 회원이 있습니다.");

				System.out.print("회원에서 탈퇴하시겠습니까(y/n) : ");
				String choice = scanner.next();
				switch (choice) {
				case "y":
					siksinMemberDAO.SiksinMemberDelete(memberEmail);
					System.out.println("회원정보가 삭제되었습니다.");
					break;
				case "n":
					System.out.println("내용을 취소합니다.");
					break;
				default:
					System.out.println("잘못입력하였습니다.");
					break;
				}
			}
			break;
		}
	}
}
