package min.java.Siksin.dbconnection.test;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import min.java.Siksin.dbcp.SiksinContext;

public class SiksinConnectionDB {

	private static final Log log = LogFactory.getLog(SiksinConnectionDB.class);

	public static void main(String[] args) {
		Connection connection = null;

		try {
			SiksinContext siksinContext = new SiksinContext();
			DataSource dataSource = siksinContext.basicDataSource;
			connection = dataSource.getConnection();
			log.info("ConnectionDB데이터베이스 연결 - " + connection);

		} catch (SQLException e) {
			log.info("ConnectionDB데이터베이스 연결 실패 - " + e);
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
