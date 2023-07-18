package min.java.Siksin.member.control;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import min.java.Siksin.DAO.SiksinMemberDAO;
import min.java.Siksin.DTO.SiksinMemberDTO;
import min.java.Siksin.action.SiksinMemberAction;

public class SiksinMemberInsert implements SiksinMemberAction {

	private static final Log log = LogFactory.getLog(SiksinMemberInsert.class);

	@Override
	public void execute(Scanner scanner) {
		System.out.println();
		System.out.println("------ ◆ 회원가입을 선택하였습니다.◆ ------");
		SiksinMemberDAO siksinMemberDAO = new SiksinMemberDAO();
		ArrayList<SiksinMemberDTO> arrayList = new ArrayList<SiksinMemberDTO>();
		
		// 테이블 입력값 조회
		arrayList = siksinMemberDAO.SiksinMemberSelectAll(); 

		System.out.println();
		System.out.println("회원정보를 입력하세요");
		log.info("Insert데이터확인 - " + arrayList);

		if (arrayList.size() >= 0) {

			SiksinMemberDTO siksinMemberDTO = new SiksinMemberDTO();
			System.out.print("이메일: ");
			String memberEmail = scanner.next();
			siksinMemberDTO = siksinMemberDAO.memberEmailcheck(memberEmail);
			System.out.println();
			if (siksinMemberDTO.getMemberEmail() == null) {
				System.out.println("중복된 이메일이 없습니다.");
				siksinMemberDTO = new SiksinMemberDTO();

				System.out.print("비밀번호: ");
				String password = scanner.next();
				System.out.print("닉네임: ");
				String nickName = scanner.next();
				System.out.print("생년월일: ");
				String memberBirth = scanner.next();
				System.out.print("성별(남=m, 여=f): ");
				String gender = scanner.next();
				System.out.print("핸드폰번호: ");
				String phoneNum = scanner.next();
				System.out.print("생활지역: ");
				String memberArea = scanner.next();

				// 자동으로 숫자 저장되게 SQL적어서 안써줘도 됨
//				siksinMemberDTO.setMemberNum(memberNum);
				
				// 입력받은값을  DTO로 저장한다
				siksinMemberDTO.setMemberEmail(memberEmail);
				siksinMemberDTO.setPassword(password);
				siksinMemberDTO.setNickName(nickName);
				siksinMemberDTO.setMemberBirth(memberBirth);
				siksinMemberDTO.setGender(gender);
				siksinMemberDTO.setPhoneNum(phoneNum);
				siksinMemberDTO.setMemberArea(memberArea);// 저장하면서 디티오로 넘어갓따가

				//위에서 저장한 DTO값을 DAO의 인설트매서드로  보내준다.
				siksinMemberDAO.SiksinMemberInsert(siksinMemberDTO);
				System.out.println("'" + nickName + "'" + " 님 회원가입이 완료되었습니다.");
				log.info("Insert회원입력 siksinMemberDTO - " + siksinMemberDTO); // 여기서 저장된 데이터 확인을 위해 get으로 불러와서 확인한다.
				return;
				
			} else {
				System.out.println("중복된 이메일이 있습니다. 다시 입력하세요.");
				log.info("Insert중복 아이디 확인 - " +siksinMemberDTO.getMemberEmail().equals(memberEmail));
				System.out.println();
			}
		}
	}
}
