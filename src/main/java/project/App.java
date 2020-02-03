package project;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.renderer.FormRenderer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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

	static private Path path;
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
		print("XD");
		
		Sql.testStatement("SELECT * FROM contactapp.contact;");

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

	public static class Logging {
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

	private static class MySQLConnection{
		static private Stage stage;
		static private Scene scene;
		static private Pane pane;
		
		public static void init() {
			App.Logging.stage = new Stage();
			App.Logging.pane = new Pane();
			App.Logging.scene = new Scene(new VBox(App.Logging.pane), 248, 400);
			App.Logging.stage.setTitle("Setup SQL Connection");
			App.Logging.stage.setScene(App.Logging.scene);
			App.Logging.stage.show();
		}
		
	}
	
}
