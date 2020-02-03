package project;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import com.sun.javafx.scene.control.IntegerField;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

		mainStage.setTitle("Contact Manager");
		mainStage.getIcons().add(new Image(path.toUri() + "resources/icon.png"));
		Pane root = new Pane();
		Pane root2 = new Pane();

		/*
		 * Form loginForm = Form.of( Group.of( Field.ofStringType(x) .label("Username"),
		 * Field.ofStringType(y) .label("Password")
		 * .required("This field can't be empty") ) ).title("Login");
		 * root.getChildren().add(new FormRenderer(loginForm));
		 */

		ListView<String> listView = new ListView<String>();
		listView.getItems().add("xd");
		listView.getItems().add("xd");
		root2.getChildren().add(listView);

		Scene scene = new Scene(new HBox(root, root2), 1280, 720);

		mainStage.setScene(scene);
		mainStage.show();

		App.Logging.init();
		App.SQLSetupConnectionWindow.init();
		App.print("XD");
		Sql.init();
		
	
		//Sql.executeLiteralStatement("SELECT * FROM contactapp.contact;");

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
			App.Logging.list = new ArrayList<String>();
			App.Logging.stage = new Stage();
			App.Logging.pane = new Pane();
			App.Logging.view = new ListView<String>();
			App.Logging.pane.getChildren().add(view);
			App.Logging.scene = new Scene(new VBox(App.Logging.pane), 248, 400);
			App.Logging.stage.setTitle("Console");
			App.Logging.stage.setScene(App.Logging.scene);
			App.Logging.stage.show();
		}

		public static void add(String s) {
			if (!App.debug)
				return;
			App.Logging.view.getItems().add(0, s);
			if (App.Logging.view.getItems().size() > App.Logging.size) {
				App.Logging.view.getItems().remove(App.Logging.size);
			}
			return;
		}

		private static void clear() {
			if (!App.debug)
				return;
			App.Logging.view.getItems().clear();
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
		    		    
		    // Add Name Label
		    Label serverLabel = new Label("Server");
		    gridPane.add(serverLabel, 0,0);
		    TextField serverField = new TextField();
		    //serverField.setText(prefDict.get("ServerName").toString());
		    serverField.setPrefHeight(40);
		    gridPane.add(serverField, 1,0);

		    Label portLabel = new Label("Port");
		    gridPane.add(portLabel, 0, 1);
		    TextField portField = new TextField();
		    portField.setPrefHeight(40);
		    gridPane.add(portField, 1, 1);

		    Label databaseLabel = new Label("Database");
		    gridPane.add(databaseLabel, 0, 2);
		    TextField databaseField = new TextField();
		    databaseField.setPrefHeight(40);
		    gridPane.add(databaseField, 1, 2);
		    
		    Label userLabel = new Label("User");
		    gridPane.add(userLabel, 0, 3);
		    TextField userField = new TextField();
		    userField.setPrefHeight(40);
		    gridPane.add(userField, 1, 3);
		    
		    Label passwordLabel = new Label("Password");
		    gridPane.add(passwordLabel, 0, 4);
		    PasswordField passwordField = new PasswordField();
		    passwordField.setPrefHeight(40);
		    gridPane.add(passwordField, 1, 4);

		    // Add Submit Button
		    Button submitButton = new Button("Submit");
		    submitButton.setPrefHeight(40);
		    submitButton.setDefaultButton(true);
		    submitButton.setPrefWidth(100);
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



















