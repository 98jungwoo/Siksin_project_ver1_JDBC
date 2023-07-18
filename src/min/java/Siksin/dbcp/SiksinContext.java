package min.java.Siksin.dbcp;

import org.apache.commons.dbcp2.BasicDataSource;

public class SiksinContext {

	public BasicDataSource basicDataSource;

	public SiksinContext() {
		System.out.println("context 동작");
		basicDataSource = new BasicDataSource();

		basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		basicDataSource.setUsername("jung_jdbc");
		basicDataSource.setPassword("1234");
		basicDataSource.setInitialSize(4);
		basicDataSource.setMaxIdle(1000);
		basicDataSource.setMinIdle(5);
	}

}
