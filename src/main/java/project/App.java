package project;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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

	public static Path path;
	static boolean debug = true;

	@Override
	public void start(Stage mainStage) {

		Test x = new Test();
		x.init();
		
		Config.load();
		App.ContactApp.init();
		App.Logging.init();
		App.NotificationWindow.init();
		App.ChoiceWindow.init();
		App.print("JavaFx init successful");

		Sql.init();
		

	}

	
	public static void main(String[] args) {

		System.out.println("Java2 class final project! " + System.getProperty("java.version"));
		path = FileSystems.getDefault().getPath("");
		System.out.println(path.toAbsolutePath());
		System.out.println(path.toAbsolutePath().resolve("oui.png").toString());
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

	public static class SQLConfigWindow {
		
		private static SQLConfigWindow controller;
		private static Scene scene;
		private static Stage stage;
		

		public class Controller{
		    @FXML
		    public TextField serverField;

		    @FXML
		    public TextField databaseField;

		    @FXML
		    public TextField userField;

		    @FXML
		    public PasswordField passwordField;

		    @FXML
		    public GridPane pane;

		    @FXML
		    public TextField portField;
		    	
		}

		public static void init() {

		}
		
		public void myInit() {

	    
			/*
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
		    
		    Label errorField = new Label("");
		    
		    submitButton.setOnAction( click -> {
		    	submitButton.setDisable(true);
		    	
		    	String formUser = userField.getText();
		    	String formServerName = serverField.getText();
		    	String formDatabase = databaseField.getText();
		    	String formPort = portField.getText();
		    	String formPassword = passwordField.getText();

		    	if (formPort.matches(Contact.Regex.NUMBER)) {
			    	int formPortInt = Integer.parseInt(formPort);
			    	
			    	//
			    	
			    	//
			    	// NEED TO CHEK INPUT
			    	if (formPortInt > 0 && formPortInt<=65535 ) {
			    		
			    		boolean proceed = true;
			    		try {
							Sql.setDataSource(formServerName, formPortInt, formDatabase, formUser,formPassword );
						} catch (SQLException e) {
							App.print("Error setting datasource");
							e.printStackTrace();
							errorField.setText(errorField.getText()+"\nCouldn't create SQL DataSource, please check your input.");
						    return;
						}
			    		
			    		if (Sql.isConnectionValid()) {
			    			
					    	Config.setKey("Username", userField.getText());
					    	Config.setKey("ServerName", serverField.getText());
					    	Config.setKey("Database", databaseField.getText());
					    	Config.setKey("Port", Integer.parseInt(portField.getText()));
					    	
					    	try {
								Sql.executeLiteralStatement("SELECT 1 FROM "+formDatabase+".contact LIMIT 1;");
								//success finding schema
								App.print("Successfuly connected to database");
								stage.hide();
						    	submitButton.setDisable(false);
								return;
							} catch (SQLException e) {
								App.print("Couldn't connect to database");
								errorField.setText(errorField.getText()+"\nCouldn't find contact database, please check your input.");
								App.ChoiceWindow.askChoice("Couldnt find contact table, would you like to create it?");
								e.printStackTrace();
						    	submitButton.setDisable(false);
							}
			    		}
			    		else {
			    			if(Sql.errorCode == 1045) {
			    				App.NotificationWindow.notify("Error in username/password combination.");
			    			}
			    			else if(Sql.errorCode == 1049) {
			    				App.ChoiceWindow.askChoice("Couldn't find database, would you like to create it?");
			    			}
			    			
			    			submitButton.setDisable(false);
							errorField.setText(errorField.getText()+"\nCouldn't connect to MySQL, please check your database exists");
							return;
			    		}
			    		
			    	}
			    	else {
					    serverField.clear();
					    databaseField.clear();
					    userField.clear();
					    passwordField.clear();
			    	}
		    	}
		    	else {
				    portField.clear();
		    	}
			
		    });
		    
		    
		    gridPane.add(submitButton, 0, 5, 2, 1);
		    GridPane.setHalignment(submitButton, HPos.CENTER);
		    GridPane.setMargin(submitButton, new Insets(20, 0,20,0));
		    
			scene = new Scene(new VBox(gridPane,errorField), 500, 400);
			stage.setTitle("Setup SQL Connection");
			stage.setScene(scene);
			stage.show();
			*/
			
		}
		
	}
	
	private static class NotificationWindow{
		static private Stage stage;
		static private Scene scene;
		static private Pane pane;
		static private Label label;
		
		public static void init() {
			stage = new Stage();
			pane = new Pane();
			label = new Label("");
			
			
			Button button = new Button("Ok");
			button.setOnAction(click -> {
				stage.hide(); 
			});
			button.setPrefHeight(40);
		    button.setPrefWidth(100);
		    
			stage.setTitle("z");
			scene = new Scene(new HBox(label,button), 200, 200);
			stage.setScene(scene);
			stage.hide();
		}
		
		public static void notify(String msg) {
			label.setText(msg);
			stage.show();
			stage.setAlwaysOnTop(true); 
			stage.setAlwaysOnTop(false);
			return;
		}
	}
	
	private static class ChoiceWindow {
		
		static private Stage stage;
		static private Scene scene;
		static private Pane pane;
		static private Label label;
		static private boolean gotChoice = false;
		static private boolean choice = false;
		
		public static void init() {
			stage = new Stage();
			pane = new Pane();
			label = new Label("");
			
			Button buttonYes = new Button("Yes");
			buttonYes.setOnAction(click -> {
				gotChoice = true;
				choice = true;
				stage.hide();
			});
			buttonYes.setPrefHeight(40);
		    buttonYes.setPrefWidth(100);
		    
			Button buttonNo = new Button("No");
			buttonNo.setOnAction(click -> {
				gotChoice = true;
				choice = false;
				stage.hide();
			});
			buttonNo.setPrefHeight(40);
			buttonNo.setPrefWidth(100);
		    
			stage.setTitle("");
			scene = new Scene(new VBox(label, new HBox(buttonYes,buttonNo)), 200, 200);
			stage.setScene(scene);
			stage.hide();
		}
		
		public static void askChoice(String s) {
			label.setText(s);
			stage.show();
			stage.setAlwaysOnTop(true); stage.setAlwaysOnTop(false);
		}
		
		public static boolean getChoice() {
			if (gotChoice) {
				if (choice) {return true;}
				else {return false;}
			}
			else {throw new IllegalStateException("Trying to get choice when no questions were asked.");}
		}
		
	}
}



















