package project;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.renderer.FormRenderer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *  Contact application project for java2 class.
 *  Origin created on 20/01/2020
 *  
 *  Jean-Hadrien Damay
 *  Lionel Mbanza 
 *  
 *  
 */
public class App extends Application
{
	
	static private Path path;
	
    @Override
    public void start(Stage stage) {
    	stage.setTitle("Contact Manager");
    	stage.getIcons().add(new Image(path.toUri()+"resources/icon.png"));
    	
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        
        Pane root = new Pane();
        Pane root2 = new Pane();
        
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        root.getChildren().add(l);

        String x = null,y = null;
        
        /*
        Form loginForm = Form.of(
                Group.of(
                        Field.ofStringType(x)
                                .label("Username"),
                        Field.ofStringType(y)
                                .label("Password")
                                .required("This field can't be empty")
                )
        ).title("Login");
        root.getChildren().add(new FormRenderer(loginForm)); */
        
        ListView<String> listView = new ListView<String>();
        listView.getItems().add("xd");
        listView.getItems().add("xd");
        root2.getChildren().add(listView);
        
        
        Scene scene = new Scene(new HBox(root,root2), 1280, 720);
        stage.setScene(scene);
        stage.show();
        
    }
        
    public static void main( String[] args )
    {
    	
        System.out.println( "Java2 class final project! " + System.getProperty("java.version") );
    	path = FileSystems.getDefault().getPath("");
    	System.out.println(path.toAbsolutePath());
    	    	    	
    	//launch();
    }
}
