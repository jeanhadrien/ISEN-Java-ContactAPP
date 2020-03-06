import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Contact application project for java2 class. Origin created on 20/01/2020
 * 
 * Jean-Hadrien Damay Lionel Mbanza
 * 
 * 
 */
public class App extends Application {

	public static Path path;
	public static Image icon = new Image(FileManager.getResourcePathAsString("icon.png"));
	public static Database localDatabase;

	@Override
	public void start(Stage mainStage) {

		localDatabase = new Database();
		Config.load();

		Error.init();
		ContactView.init();
		SQLConfigWindow.init();

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

			stage.getIcons().add(App.icon);
			scene = new Scene(new HBox(pane), 1280, 720);
			stage.setScene(scene);
			stage.show();
			
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



















