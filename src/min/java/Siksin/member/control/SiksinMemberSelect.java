package min.java.Siksin.member.control;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import min.java.Siksin.DAO.SiksinMemberDAO;
import min.java.Siksin.DTO.SiksinMemberDTO;
import min.java.Siksin.action.SiksinMemberAction;

public class SiksinMemberSelect implements SiksinMemberAction {

	private static final Log log = LogFactory.getLog(SiksinMemberSelect.class);

	@Override
	public void execute(Scanner scanner) {
		System.out.println("------ ◆ 회원조회를 선택하였습니다.◆ ------");
		System.out.println("[1] 회원조회  | [2] 회원상세조회");
		System.out.print("◆ 원하는 번호를 입력하세요 : ");
		int choice = scanner.nextInt();
		if (choice == 1) {

			ArrayList<SiksinMemberDTO> arrayList = new ArrayList<SiksinMemberDTO>();
			SiksinMemberDAO siksinMemberDAO = new SiksinMemberDAO();
			arrayList = siksinMemberDAO.SiksinMemberSelectAll();
			log.info("Select데이터 확인 - " + arrayList);
			System.out.println("[회원정보]");

			for (SiksinMemberDTO siksinMemberDTO : arrayList) {
				int memberNum = siksinMemberDTO.getMemberNum();
				String memberEmail = siksinMemberDTO.getMemberEmail();
				String nickName = siksinMemberDTO.getNickName();
				String gender = siksinMemberDTO.getGender();

				System.out.println(
						memberNum + "번" + " | 이메일: " + memberEmail + " | 닉네임: " + nickName + " | 성별: " + gender);
			}

		} else if (choice == 2) {
			SiksinMemberSelectDetail siksinMemberSelectDetail = new SiksinMemberSelectDetail();
			siksinMemberSelectDetail.execute(scanner);
		} else {
			System.out.println("번호를 다시 입력하세요");
			SiksinMemberSelect siksinMemberSelect = new SiksinMemberSelect();
			siksinMemberSelect.execute(scanner);
		}
	}

}
