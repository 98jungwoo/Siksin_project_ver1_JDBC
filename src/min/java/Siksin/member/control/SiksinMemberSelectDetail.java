package min.java.Siksin.member.control;

import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import min.java.Siksin.DAO.SiksinMemberDAO;
import min.java.Siksin.DTO.SiksinMemberDTO;
import min.java.Siksin.action.SiksinMemberAction;

public class SiksinMemberSelectDetail implements SiksinMemberAction {

	private static final Log log = LogFactory.getLog(SiksinMemberSelectDetail.class);

	@Override
	public void execute(Scanner scanner) {

		do {
			SiksinMemberDTO siksinMemberDTO = new SiksinMemberDTO();
			SiksinMemberDAO siksinMemberDAO = new SiksinMemberDAO();
			System.out.println("------ ◆ 회원정보 상세보기를 선택하였습니다.◆ ------");
			System.out.print("회원 이메일: ");
			String memberEmail = scanner.next();

			siksinMemberDTO = siksinMemberDAO.SiksinMemberSelectD(memberEmail);

			int memberNum = siksinMemberDTO.getMemberNum();
			memberEmail = siksinMemberDTO.getMemberEmail();
			String password = siksinMemberDTO.getPassword();
			String nickName = siksinMemberDTO.getNickName();
			String memberBirth = siksinMemberDTO.getMemberBirth();
			String gender = siksinMemberDTO.getGender();
			String phoneNum = siksinMemberDTO.getPhoneNum();
			String memberArea = siksinMemberDTO.getMemberArea();

			if (siksinMemberDTO.getMemberEmail() == null) {
				System.out.println();
				System.out.println("존재하는 회원이 없습니다.");

			} else {
				log.info("SelectDetail아이디 확인 - " + siksinMemberDTO.getMemberEmail().equals(memberEmail));
				System.out.println("---------------------------");
				System.out.println("회원번호	: " + memberNum);
				System.out.println("이메일	: " + memberEmail);
				System.out.println("비밀번호	: " + password);
				System.out.println("닉네임	: " + nickName);
				System.out.println("생년월일	: " + memberBirth);
				System.out.println("성별	: " + gender);
				System.out.println("핸드폰번호	: " + phoneNum);
				System.out.println("생활지역	: " + memberArea);
				System.out.println("---------------------------");
				return;

			}
		} while (true);

	}
}
