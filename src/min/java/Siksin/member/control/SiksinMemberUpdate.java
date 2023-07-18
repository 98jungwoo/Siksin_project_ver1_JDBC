package min.java.Siksin.member.control;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import min.java.Siksin.DAO.SiksinMemberDAO;
import min.java.Siksin.DTO.SiksinMemberDTO;
import min.java.Siksin.action.SiksinMemberAction;

public class SiksinMemberUpdate implements SiksinMemberAction {

	private static final Log log = LogFactory.getLog(SiksinMemberUpdate.class);

	@Override
	public void execute(Scanner scanner) {
		System.out.println();
		System.out.println("------ ◆ 회원정보 수정을 선택하였습니다.◆ ------");

		SiksinMemberDAO siksinMemberDAO = new SiksinMemberDAO();
		ArrayList<SiksinMemberDTO> arrayList = new ArrayList<SiksinMemberDTO>();
		arrayList = siksinMemberDAO.SiksinMemberSelectAll();
		System.out.println("회원정보를 수정하세요");

		for (SiksinMemberDTO siksinMemberDTO : arrayList) {
			System.out.print("회원 이메일: ");
			String memberEmail = scanner.next();
			
			siksinMemberDTO = siksinMemberDAO.memberEmailcheck(memberEmail);
			
			System.out.println(siksinMemberDTO);
			System.out.println();
			if (siksinMemberDTO.getMemberEmail() == null) {
				System.out.println("이메일이 없습니다. 다시 입력하세요.");
			} else {
				log.info("Update이메일 확인 - " + siksinMemberDTO.getMemberEmail().equals(memberEmail));
				System.out.println();
				System.out.println("이메일이 있습니다. 회원정보수정을 진행해주세요.");
				siksinMemberDTO = siksinMemberDAO.SiksinMemberSelectD(memberEmail);
				log.info("Update회원정보siksinMemberDTO" + siksinMemberDTO);
				System.out.println("[회원정보 수정]");
				System.out.println("비밀번호: " + siksinMemberDTO.getPassword());
				System.out.print("수정할 비밀번호: ");
				String password = scanner.next();
				System.out.println("닉네임: " + siksinMemberDTO.getNickName());
				System.out.print("수정할 닉네임: ");
				String nickName = scanner.next();
				System.out.println("핸드폰번호: " + siksinMemberDTO.getPhoneNum());
				System.out.print("수정할 핸드폰번호: ");
				String phoneNum = scanner.next();
				System.out.println("생활지역:" + siksinMemberDTO.getMemberArea());
				System.out.print("수정할 생활지역: ");
				String memberArea = scanner.next();
				
				siksinMemberDTO.setMemberEmail(memberEmail);
				siksinMemberDTO.setPassword(password);
				siksinMemberDTO.setNickName(nickName);
				siksinMemberDTO.setPhoneNum(phoneNum);
				siksinMemberDTO.setMemberArea(memberArea);
//				System.out.println(siksinMemberDTO);
				siksinMemberDAO.SiksinMemberUpdate(memberEmail, password, nickName, phoneNum, memberArea);
				System.out.println(nickName + " 님의 회원정보가 수정되었습니다.");
				log.info("Update회원수정siksinMemberDTO " + siksinMemberDTO);
				break;
			}

		}
	}
}
