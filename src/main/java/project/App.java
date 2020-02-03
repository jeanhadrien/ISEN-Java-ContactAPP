package project;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Contact application project for java2 class. Origin created on 20/01/2020
 * 
 * Jean-Hadrien Damay Lionel Mbanza
 * 
 * 
 */
public class App extends Application {

	static protected Path path;
	static boolean debug = true;

	@Override
	public void start(Stage mainStage) {

		Config.load();
		
		App.ContactApp.init();
		App.Logging.init();
		App.SQLSetupConnectionWindow.init();
		App.print("JavaFx init successful");

		Sql.init();
		

	}

	
	public static void main(String[] args) {

		System.out.println("Java2 class final project! " + System.getProperty("java.version"));
		path = FileSystems.getDefault().getPath("");
		System.out.println(path.toAbsolutePath());
		
		// recuperer sql connection

		// recuperer contactapp et la table contact ou creer
		
		// recuperer les donnees
		

		launch();
	}

	public static void print(String s) {
		App.Logging.add(s);
	}
	
	private static class ContactApp{
		static private Stage stage;
		static private Scene scene;
		static private Pane pane;
		
		public static void init() {
			stage = new Stage();
			pane = new Pane();
			
			ListView<String> listView = new ListView<String>();
			listView.getItems().add("xd");
			listView.getItems().add("xd");
			pane.getChildren().add(listView);

			stage.setTitle("Contact Manager");
			stage.getIcons().add(new Image(path.toUri() + "resources/icon.png"));
			scene = new Scene(new HBox(pane), 1280, 720);
			stage.setScene(scene);
			stage.show();
			
		}
	}
	
	private static class Logging {
		static private List<String> list;
		static private int size = 100;
		static private Stage stage;
		static private Scene scene;
		static private Pane pane;
		static private ListView<String> view;

		public static void init() {
			if (!App.debug)
				return;
			list = new ArrayList<String>();
			stage = new Stage();
			pane = new Pane();
			view = new ListView<String>();
			pane.getChildren().add(view);
			scene = new Scene(new VBox(pane), 248, 400);
			stage.setTitle("Console");
			stage.setScene(scene);
			stage.show();
			
		}

		public static void add(String s) {
			if (!App.debug)
				return;
			view.getItems().add(0, s);
			if (view.getItems().size() > size) {
				view.getItems().remove(size);
			}
			return;
		}

		private static void clear() {
			if (!App.debug)
				return;
			view.getItems().clear();
		}

	}

	
	private static class SQLSetupConnectionWindow{
		static private Stage stage;
		static private Scene scene;
		static private Pane pane;
		
		public static void init() {
			stage = new Stage();
			pane = new Pane();
		    // Instantiate a new Grid Pane
		    GridPane gridPane = new GridPane();

		    // Position the pane at the center of the screen, both vertically and horizontally
		    gridPane.setAlignment(Pos.CENTER);

		    // Set a padding of 20px on each side
		    gridPane.setPadding(new Insets(40, 40, 40, 40));

		    // Set the horizontal gap between columns
		    gridPane.setHgap(10);

		    // Set the vertical gap between rows
		    gridPane.setVgap(10);

		    // Add Column Constraints

		    // columnOneConstraints will be applied to all the nodes placed in column one.
		    ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
		    columnOneConstraints.setHalignment(HPos.RIGHT);

		    // columnTwoConstraints will be applied to all the nodes placed in column two.
		    ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
		    columnTwoConstrains.setHgrow(Priority.ALWAYS);

		    gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);
		    		    
		    Label serverLabel = new Label("Server");
		    gridPane.add(serverLabel, 0,0);
		    TextField serverField = new TextField();
		    serverField.setText(Config.getKey("ServerName").toString());
		    serverField.setPrefHeight(40);
		    gridPane.add(serverField, 1,0);

		    Label portLabel = new Label("Port");
		    gridPane.add(portLabel, 0, 1);
		    TextField portField = new TextField();
		    portField.setText(Config.getKey("Port").toString());
		    portField.setPrefHeight(40);
		    gridPane.add(portField, 1, 1);

		    Label databaseLabel = new Label("Database");
		    gridPane.add(databaseLabel, 0, 2);
		    TextField databaseField = new TextField();
		    databaseField.setText(Config.getKey("Database").toString());
		    databaseField.setPrefHeight(40);
		    gridPane.add(databaseField, 1, 2);
		    
		    Label userLabel = new Label("User");
		    gridPane.add(userLabel, 0, 3);
		    TextField userField = new TextField();
		    userField.setText(Config.getKey("Username").toString());

		    userField.setPrefHeight(40);
		    gridPane.add(userField, 1, 3);
		    
		    Label passwordLabel = new Label("Password");
		    gridPane.add(passwordLabel, 0, 4);
		    PasswordField passwordField = new PasswordField();
		    passwordField.setPrefHeight(40);
		    gridPane.add(passwordField, 1, 4);

		    // Add Submit Button
		    Button submitButton = new Button("CONNECT");
		    submitButton.setPrefHeight(40);
		    submitButton.setDefaultButton(true);
		    submitButton.setPrefWidth(100);
		    
		    submitButton.setOnAction( click -> {
		    	
		    	String formUser = userField.getText();
		    	String formServerName = serverField.getText();
		    	String formDatabase = databaseField.getText();
		    	String formPort = portField.getText();
		    	String formPassword = passwordField.getText();

		    	if (formPort.matches(Contact.Regex.NUMBER)) {
			    	int formPortInt = Integer.parseInt(formPort);
			    	
			    	if (formPortInt > 0 && formPortInt<=65535
			    			&& (formUser+formServerName+formDatabase).matches(Contact.Regex.BASIC) ) {
			    		try {
							Sql.setDataSource(formServerName, formPortInt, formDatabase, formUser,formPassword );
						} catch (SQLException e) {
							App.print("Error setting datasource");
							e.printStackTrace();
						}
			    		finally {
					    	Config.setKey("Username", userField.getText());
					    	Config.setKey("ServerName", serverField.getText());
					    	Config.setKey("Database", databaseField.getText());
					    	Config.setKey("Port", Integer.parseInt(portField.getText()));
							Sql.executeLiteralStatement("SELECT * FROM contactapp.contact;");

			    		}
			    		
			    	}
		    	}
		    	
		    	else {
				    serverField.clear();
				    portField.clear();
				    databaseField.clear();
				    userField.clear();
				    passwordField.clear();
		    	}

		    });
		    
		    
		    gridPane.add(submitButton, 0, 5, 2, 1);
		    GridPane.setHalignment(submitButton, HPos.CENTER);
		    GridPane.setMargin(submitButton, new Insets(20, 0,20,0));
		    
			scene = new Scene(gridPane, 500, 400);
			stage.setTitle("Setup SQL Connection");
			stage.setScene(scene);
			stage.show();
			
			
		}
		
		
		
	}
	
}



















