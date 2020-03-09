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
 * Contact application project for java2 class.
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
		ContactEdit.init();
		SQLConfigWindow.init();
		Sql.init();

	}

	public static void main(String[] args) {
		System.out.println("Java2 class final project! " + System.getProperty("java.version"));
		path = FileSystems.getDefault().getPath("");
		System.out.println(path.toAbsolutePath());
		launch();
	}


}



















