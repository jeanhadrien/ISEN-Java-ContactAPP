package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class Sql {

	private static MysqlDataSource dataSource;

	public static DataSource getDataSource() throws SQLException {
		if (dataSource == null) {
			dataSource = new MysqlDataSource();
			dataSource.setServerName("localhost");
			dataSource.setPort(3306);
			dataSource.setDatabaseName("contactapp");
			dataSource.setUser("root");
			dataSource.setPassword("jhdd");
			dataSource.setServerTimezone("UTC");
			}
		return dataSource;
	}
	
	public static ResultSet testStatement(String s) {
		try (Connection connection = Sql.getDataSource().getConnection()) {
			
			try(PreparedStatement statement = connection.prepareStatement(s)){
				//statement.setString(parameterIndex, x);
				
				try(ResultSet results = statement.executeQuery()){
					App.print(":))");
					return results;
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
}
