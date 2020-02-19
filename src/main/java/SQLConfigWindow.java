import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SQLConfigWindow {
	
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
			loader = new FXMLLoader(FileManager.getResource("SQLConfigWindow.fxml"));
			parent = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controller = loader.getController();

		scene = new Scene(parent);
    
        stage.setTitle("SQL Config");
        stage.setScene(scene);
        stage.show();
	}
	
	public static void getForm(SQLConfigWindow.Form form) {
		
		
	}
	
	
	public static void filterForm() {
		
	}
	
	
	
	
}
