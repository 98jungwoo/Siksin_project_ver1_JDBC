package min.java.Siksin.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import min.java.Siksin.DTO.SiksinMemberDTO;
import min.java.Siksin.dbcp.SiksinContext;
import min.java.Siksin.service.SiksinMemberService;

public class SiksinMemberDAO implements SiksinMemberService {

	private static final Log log = LogFactory.getLog(SiksinMemberDAO.class);

	@Override
	public ArrayList<SiksinMemberDTO> SiksinMemberSelectAll() {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<SiksinMemberDTO> arrayList = new ArrayList<SiksinMemberDTO>();

		try {
			SiksinContext siksinContext = new SiksinContext();
			DataSource dataSource = siksinContext.basicDataSource;
			connection = dataSource.getConnection();

			String sql = "select memberNum, memberEmail, password, nickName, to_char(memberBirth, 'yyyy-mm-dd')memberBirth , gender, phoneNum, memberArea from siksinMember ";
			/* to_char(memberBirth, 'yyyy-mm-dd')memberBirth(뒤에잇는 맴버벌스는 별칭을 의미함) */
			
			log.info("DAO_SelectAll_SQL - " + sql);

			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery(); // PreparedStatement 개체에서 SQL 쿼리를 실행하고 쿼리에 의해 생성된 ResultSet 개체를 반환합니다.

			// 커서를 현재 위치에서 한 행 앞으로 이동하면서 반복한다.
			while (resultSet.next()) {
				SiksinMemberDTO siksinMemberDTO = new SiksinMemberDTO();
				siksinMemberDTO.setMemberNum(resultSet.getInt("memberNum"));
				siksinMemberDTO.setMemberEmail(resultSet.getString("memberEmail"));
				log.info("DAO_SelectAll회원 이메일 확인" + resultSet.getString("memberEmail"));
				siksinMemberDTO.setPassword(resultSet.getString("password"));
				siksinMemberDTO.setNickName(resultSet.getString("nickName"));
				siksinMemberDTO.setMemberBirth(resultSet.getString("memberBirth"));
				siksinMemberDTO.setGender(resultSet.getString("gender"));
				siksinMemberDTO.setPhoneNum(resultSet.getString("phoneNum"));
				siksinMemberDTO.setMemberArea(resultSet.getString("memberArea"));

				arrayList.add(siksinMemberDTO);// 내가 디티오에서 호출해서 값을 저장한걸 어레이리스트에 저장한다.
				log.info("DAO_SelectAll 조회 데이터 확인 - " + siksinMemberDTO);
			}
			resultSet.getRow();
			if (resultSet.getRow() == 0) {
				log.info("DAO_SelectAll등록한 회원이 없습니다.");
			}

		} catch (Exception e) {
			log.info("DAO_SelectAll전체조회 실패 (예외확인) - " + e);

		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arrayList;

	}

	@Override
	public void SiksinMemberInsert(SiksinMemberDTO siksinMemberDTO) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

//		내가 DTO로 바로 매개변수로 받았기 때문에 DTO로 저장하는 이코드는 필요가 없어
//		SiksinMemberDTO siksinMemberDTO = new SiksinMemberDTO();
//		siksinMemberDTO.setMemberNum(memberNum);
//		siksinMemberDTO.setMemberEmail(memberEmail);
//		siksinMemberDTO.setPassword(password);
//		siksinMemberDTO.setNickName(nickName);
//		siksinMemberDTO.setMemberBirth(memberBirth);
//		siksinMemberDTO.setGender(gender);
//		siksinMemberDTO.setPhoneNum(phoneNum);
//		siksinMemberDTO.setMemberArea(memberArea);

		try {
//			System.out.println("DAO-inser");

			// 내가 만든 연결 클래스를 인스턴스화해야함
			SiksinContext siksinContext = new SiksinContext();
			DataSource dataSource = siksinContext.basicDataSource;
			connection = dataSource.getConnection();

			String sql = "insert into siksinMember(memberNum, memberEmail, password, nickName, memberBirth, gender, phoneNum, memberArea) ";
			sql += " values(board_seq.nextval, ?, ?, ?, ?, ?, ?, ?) ";
			log.info("DAO_inser_SQL - " + sql);
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, siksinMemberDTO.getMemberEmail());
			preparedStatement.setString(2, siksinMemberDTO.getPassword());
			preparedStatement.setString(3, siksinMemberDTO.getNickName());
			preparedStatement.setString(4, siksinMemberDTO.getMemberBirth());
			preparedStatement.setString(5, siksinMemberDTO.getGender());
			preparedStatement.setString(6, siksinMemberDTO.getPhoneNum());
			preparedStatement.setString(7, siksinMemberDTO.getMemberArea());

			int count = preparedStatement.executeUpdate();
			log.info("DAO_inser_입력 데이터 확인 - " + siksinMemberDTO);
			
			if (count > 0) {
				connection.commit();
				log.info("DAO_inser_커밋되었습니다.");
			} else {
				connection.rollback();
				log.info("DAO_inser_롤백되었습니다.");
			}
		} catch (SQLException e) {
			log.info("DAO_inser_회원 입력 실패 - " + e);
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public SiksinMemberDTO SiksinMemberUpdate(String memberEmail, String password, String nickName, String phoneNum,
			String memberArea) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		// 여기는 매개변수를 각각 받았기 때문에 이걸 써줘야해
		SiksinMemberDTO siksinMemberDTO = new SiksinMemberDTO();
		siksinMemberDTO.setMemberEmail(memberEmail);
		siksinMemberDTO.setPassword(password);
		siksinMemberDTO.setNickName(nickName);
		siksinMemberDTO.setPhoneNum(phoneNum);
		siksinMemberDTO.setMemberArea(memberArea);
		try {
			SiksinContext siksinContext = new SiksinContext(); // 내가 만든 연결 클래스를 인스턴스화해야함
			DataSource dataSource = siksinContext.basicDataSource;
			connection = dataSource.getConnection();
			String sql = "update siksinMember set password = ? , nickName = ?,  phoneNum = ? , memberArea = ?  where  memberEmail = ?";

			log.info("DAO_Update_SQL - " + sql);
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, siksinMemberDTO.getPassword());
			preparedStatement.setString(2, siksinMemberDTO.getNickName());
			preparedStatement.setString(3, siksinMemberDTO.getPhoneNum());
			preparedStatement.setString(4, siksinMemberDTO.getMemberArea());
			preparedStatement.setString(5, siksinMemberDTO.getMemberEmail());
			int count = preparedStatement.executeUpdate();

			log.info("DAO_Update_수정데이터 확인 - " + siksinMemberDTO);
			if (count > 0) {
				connection.commit();
				log.info("DAO_Update_커밋되었습니다.");
			} else {
				connection.rollback();
				log.info("DAO_Update_롤백되었습니다.");

			}
		} catch (Exception e) {
			log.info("DAO_Update_회원수정 실패 - " + e);
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return siksinMemberDTO;
	}

	@Override
	public SiksinMemberDTO SiksinMemberDelete(String memberEmail) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		SiksinMemberDTO siksinMemberDTO = new SiksinMemberDTO();
		siksinMemberDTO.setMemberEmail(memberEmail);

		try {
			SiksinContext siksinContext = new SiksinContext(); // 내가 만든 연결 클래스를 인스턴스화해야함
			DataSource dataSource = siksinContext.basicDataSource;
			connection = dataSource.getConnection();
			String sql = "delete from siksinMember where memberEmail = ? ";

			log.info("DAO_Delete_SQL - " + sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, siksinMemberDTO.getMemberEmail());

			int count = preparedStatement.executeUpdate();// insert, update, delete 에서 preparedStatement 객체에서 sql문을 싱행합니다.

			if (count > 0) {
				connection.commit();
				log.info("DAO_Delete_커밋되었습니다.");
			} else {
				connection.rollback();
				log.info("DAO_Delete_롤백되었습니다.");
			}
		} catch (SQLException e) {
			log.info("DAO_Delete_회원 삭제 실패 - " + e);
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return siksinMemberDTO;
	}

	@Override
	public SiksinMemberDTO SiksinMemberSelectD(String memberEmail) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		SiksinMemberDTO siksinMemberDTO = new SiksinMemberDTO();

		try {
			SiksinContext siksinContext = new SiksinContext(); // 내가 만든 연결 클래스를 인스턴스화해야함
			DataSource dataSource = siksinContext.basicDataSource;
			connection = dataSource.getConnection();

			String sql = "select memberNum, memberEmail, password, nickName, to_char(memberBirth, 'yyyy-mm-dd')memberBirth, gender, phoneNum, memberArea from siksinMember ";
			sql += " where memberEmail = ? ";

			log.info("DAO_SelectD_SQL - " + sql);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberEmail);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				siksinMemberDTO.setMemberNum(resultSet.getInt("memberNum"));
				siksinMemberDTO.setMemberEmail(resultSet.getString("memberEmail"));
				siksinMemberDTO.setPassword(resultSet.getString("password"));
				siksinMemberDTO.setNickName(resultSet.getString("nickName"));
				siksinMemberDTO.setMemberBirth(resultSet.getString("memberBirth"));
				siksinMemberDTO.setGender(resultSet.getString("gender"));
				siksinMemberDTO.setPhoneNum(resultSet.getString("phoneNum"));
				siksinMemberDTO.setMemberArea(resultSet.getString("memberArea"));

			}
		} catch (SQLException e) {
			log.info("DAO_SelectD_개별 회원 조회 실패 - " + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return siksinMemberDTO;

	}

	@Override
	public SiksinMemberDTO memberEmailcheck(String memberEmail) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		SiksinMemberDTO siksinMemberDTO = new SiksinMemberDTO();

		try {
			SiksinContext siksinContext = new SiksinContext(); // 내가 만든 연결 클래스를 인스턴스화해야함
			DataSource dataSource = siksinContext.basicDataSource;
			connection = dataSource.getConnection();

			String sql = "select * from siksinMember where memberEmail = ?";

			log.info("DAO_Emailcheck_SQL - " + sql);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberEmail);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				if (resultSet.getString("memberEmail").equals(memberEmail)) {
					siksinMemberDTO.setMemberEmail(resultSet.getString("memberEmail"));
					log.info("DAO_Emailcheck_이메일 확인 - " + resultSet.getString("memberEmail"));
				}
			}

		} catch (Exception e) {
			log.info("DAO_Emailcheck_회원 아이디 체크 실패 - " + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return siksinMemberDTO;
	}

}
