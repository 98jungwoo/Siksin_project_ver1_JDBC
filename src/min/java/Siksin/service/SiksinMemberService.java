package min.java.Siksin.service;

import java.util.ArrayList;
import min.java.Siksin.DTO.SiksinMemberDTO;

public interface SiksinMemberService {

	public ArrayList<SiksinMemberDTO> SiksinMemberSelectAll();

	public void SiksinMemberInsert(SiksinMemberDTO siksinMemberDTO);

	public SiksinMemberDTO SiksinMemberUpdate(String memberEmail, String password, String nickName, String phoneNum,
			String memberArea);

	public SiksinMemberDTO SiksinMemberDelete(String memberEmail);

	public SiksinMemberDTO SiksinMemberSelectD(String memberEmail);

	public SiksinMemberDTO memberEmailcheck(String memberEmail);

}
