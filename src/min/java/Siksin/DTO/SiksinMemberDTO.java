package min.java.Siksin.DTO;

public class SiksinMemberDTO {
	private int memberNum;
	private String memberEmail;
	private String password;
	private String nickName;
	private String memberBirth;
	private String gender;
	private String phoneNum;
	private String memberArea;
	
	public int getMemberNum() {
		System.out.println("DTO- getMemberNum");
		return memberNum;
	}
	public void setMemberNum(int memberNum) {
		System.out.println("DTO- setMemberNum");
		this.memberNum = memberNum;
	}
	public String getMemberEmail() {
		System.out.println("DTO- getEmail");
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		System.out.println("DTO- setEmail");
		this.memberEmail = memberEmail;
	}
	public String getPassword() {
//		System.out.println("DTO- getPassword");
		return password;
	}
	public void setPassword(String password) {
//		System.out.println("DTO- setPassword");
		this.password = password;
	}
	public String getNickName() {
//		System.out.println("DTO에서 값 확인get"+nickName);
		return nickName;
	}
	public void setNickName(String nickName) {
//		System.out.println("DTO에서 값 확인set"+nickName);
		this.nickName = nickName;
	}
	public String getMemberBirth() {
		return memberBirth;
	}
	public void setMemberBirth(String memberBirth) {
		this.memberBirth = memberBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getMemberArea() {
		return memberArea;
	}
	public void setMemberArea(String memberArea) {
		this.memberArea = memberArea;
	}
	
	@Override
	public String toString() {
		return "SiksinMemberDTO [memberNum=" + memberNum + ", memberEmail=" + memberEmail + ", password=" + password
				+ ", nickName=" + nickName + ", memberBirth=" + memberBirth + ", gender=" + gender + ", phoneNum="
				+ phoneNum + ", memberArea=" + memberArea + "]";
	}
	
}
