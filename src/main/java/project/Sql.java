package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class Sql {

	private static MysqlDataSource dataSource;
	private static Preferences prefs;

	public static void init() {
		JSONObject obj = new JSONObject();
		JSONParser pars = new JSONParser();
		JSONObject json = null;
		
		Path mypath = App.path.resolve("resources/sqlconfig.json").toAbsolutePath();
		
		try(FileReader reader = new FileReader(mypath.toString())){
			json = (JSONObject) pars.parse(reader);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		System.out.println(json.get("Username"));
	}
	
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
	
	
	private static void setPrefs() {
		
	
		/*prefs.put("ServerName", dataSource.getServerName());
		prefs.putInt("Port", dataSource.getPort());
		prefs.put("DatabaseName", dataSource.getDatabaseName());
		prefs.put("Username", dataSource.getUser());*/
		
	}

	public static void getPrefs() {
		
		/*
		Dictionary<String, Object> dict = new Hashtable<String, Object>();
		dict.put("tst","x");

		String x = prefs.get("Username", "root");
		dict.put("Username",prefs.get("Username", "root"));
		dict.put("ServerName",prefs.get("ServerName", "localhost"));
		dict.put("DatabaseName",prefs.get("DatabaseName", "contactapp"));
		dict.put("Port",prefs.getInt("Port", 3306));
		return dict;*/
	}
	
	
	
	public static ResultSet executeLiteralStatement(String s) {
		try (Connection connection = Sql.getDataSource().getConnection()) {
			
			try(PreparedStatement statement = connection.prepareStatement(s)){
				//statement.setString(parameterIndex, x);
				
				try(ResultSet results = statement.executeQuery()){
					App.print("SUCCESS : "+s);
					return results;
				}
			}		
			
			
		} catch (SQLException e) {
			App.print("Error connecting to database");
			e.printStackTrace();
		} 
		return null;

	}
	
}
