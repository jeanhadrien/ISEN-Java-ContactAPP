package project;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Test {

    public static class Controller {
        @FXML
        private TextField serverField;

        @FXML
        private TextField databaseField;

        @FXML
        private TextField userField;

        @FXML
        private PasswordField passwordField;

        @FXML
        private GridPane pane;

        @FXML
        private TextField portField;

    }
    
    
    Test(){
    	
    }
    
    public void init() {
		Stage stage = new Stage();
		Parent root = null;
		FXMLLoader test = null;
		try {
			test = new FXMLLoader(getClass().getResource("SQLConfigWindow.fxml"));
			root = test.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Test x = test.getController();

        Scene scene = new Scene(root, 300, 275);
    
        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();

    }
    
}
