import java.io.IOException;

import com.mysql.cj.jdbc.MysqlDataSource;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.sql.DataSource;

/*
 * GUI : Manage SQL Configuration Window
 */
public class SQLConfigWindow {

	/*
	 * A form contains raw String data entered by user for the SQL connection
	 */
	public static class Form {

		Form (TextField serv, TextField db, TextField usr, PasswordField pw, TextField port){
			this.serverField = serv.getText();
			this.databaseField = db.getText();
			this.userField = usr.getText();
			this.passwordField = pw.getText();
			this.portField = port.getText();
		}

	    public String serverField;
	    public String databaseField;
	    public String userField;
	    public String passwordField;
	    public String portField;
	    
	}
	
	private static Stage stage;
	private static Parent parent;
	private static Scene scene;
	private static SQLConfigWindowController controller;
	
	public static void init() {
		stage = new Stage();
		parent = null;
		FXMLLoader loader = null;

		try {
			loader = new FXMLLoader(FileManager.getResourceURL("SQLConfigWindow.fxml"));
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		controller = loader.getController();
		scene = new Scene(parent);
        stage.setTitle("SQL Config");
        stage.setScene(scene);
        stage.show();
	}

	public static void gotForm(Form form){
		controller.disableForm();
		//TODO sanitize form
		Sql.connect(toDatasource(form));
	}

	public static void connectionValid(){
		//TODO hideWindow
	}

	public static void connectionInvalid(){
		controller.enableForm();
		//TODO clean fields
	}

	public static DataSource toDatasource(Form form){
		// at this point form should be valid
		MysqlDataSource myDataSource = new MysqlDataSource();
		myDataSource.setServerName(form.serverField);
		myDataSource.setPort(Integer.parseInt(form.portField));
		myDataSource.setDatabaseName(form.databaseField);
		myDataSource.setUser(form.userField);
		myDataSource.setPassword(form.passwordField);
		return myDataSource;
	}

	public static boolean matchesRegex(Form form) {
		if(form.databaseField.matches(Regex.BASIC)
			&& form.portField.matches(Regex.NUMBER_WHOLE)
			&& form.serverField.matches(Regex.IPV4)
			&& form.userField.matches(Regex.USERNAME)){
			return true;
		}
		else return false;
	}
	
	
	
	
}
