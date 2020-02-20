
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class Sql {

	private static MysqlDataSource dataSource;
	public static int errorCode = 0;
	private static boolean connected = false;

	public static void init() {
	}

	public static void connect(DataSource dataSource){

	}

	private static void setDataSource(String server,int port, String db, String user, String pass) throws SQLException {
		MysqlDataSource myDataSource = new MysqlDataSource();
		myDataSource.setServerTimezone("UTC");
		myDataSource.setServerName(server);
		myDataSource.setPort(port);
		myDataSource.setDatabaseName(db);
		myDataSource.setUser(user);
		myDataSource.setPassword(pass);
		
		dataSource = myDataSource;
	}

	private static DataSource getDataSource() {
		return dataSource;
	}
	
	public static boolean isConnectionValid() {
		try (Connection connection = Sql.getDataSource().getConnection()) {
			if (connection.isClosed()){
				return false;
			}
		} catch (SQLException e) {
			errorCode = e.getErrorCode();
			return false;
		}
		return true;
	}
	
	public static ResultSet executeLiteralStatement(String s) throws SQLException {
		try (Connection connection = Sql.getDataSource().getConnection()) {
			
			try(PreparedStatement statement = connection.prepareStatement(s)){
				//statement.setString(parameterIndex, x);
				
				try(ResultSet results = statement.executeQuery()){
					return results;
				}
			}
			
			
		}

	}
	
}
