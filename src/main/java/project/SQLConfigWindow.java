package project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SQLConfigWindow {
	
	private Stage stage;
	private Scene scene;
	private GridPane root;
	
	SQLConfigWindow(){
		/*
	    FXMLLoader loader = new FXMLLoader();
	    String fxmlDocPath = "/src/main/java/SQLConfigWindow.fxml";
	    FileInputStream fxmlStream = null;
		try {
			fxmlStream = new FileInputStream(App.path.toAbsolutePath().toString()+fxmlDocPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    // Create the Pane and all Details
	    try {
			controller = loader.load(fxmlStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    // Create the Scene
	    scene = new Scene(controller);
	    // Set the Scene to the Stage
	    stage.setScene(scene);
	    // Set the Title to the Stage
	    stage.setTitle("A simple FXML Example");
	    // Display the Stage
	    stage.show(); 
	    */
        // Create the FXMLLoader 
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        String fxmlDocPath = "/src/main/java/SQLConfigWindow.fxm";
        FileInputStream fxmlStream = null;
		try {
			fxmlStream = new FileInputStream(fxmlDocPath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			root = (GridPane) loader.load(fxmlStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Create the Pane and all Details
 
        // Create the Scene
        scene = new Scene(root);
        // Set the Scene to the Stage
        stage.setScene(scene);
        // Set the Title to the Stage
        stage.setTitle("A FXML Example with a Controller");
        // Display the Stage
        stage.show();
	}
	
	
	public class Controller{
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
	    
	    @FXML
	    private void initialize() 
	    {
	    }
	     
	    @FXML
	    private void printOutput() 
	    {
	    	System.out.println(userField.getText());
	    }
	}

	
	
}
