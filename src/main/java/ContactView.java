import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Stage;

import java.io.IOException;

public class ContactView {

    private static Stage stage;
    public static Parent parent;
    private static Scene scene;
    public static ContactViewController controller;

    public static void init() {
        stage = new Stage();
        stage.getIcons().add(App.icon);
        stage.setResizable(false);
        parent = null;
        FXMLLoader loader = null;

        try {
            loader = new FXMLLoader(FileManager.getResourceURL("fxml/ContactView.fxml"));
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Object temp = loader.getController();
        controller = (ContactViewController) temp;
        isNotConnected();
        scene = new Scene(parent);
        stage.setTitle("SQL Config");
        stage.setScene(scene);
        stage.show();
    }

    public static void isConnected(){
        parent.setEffect(null);
        parent.setDisable(false);
    }

    public static void isNotConnected(){
        ColorAdjust adj = new ColorAdjust(0, 0, -0.05, 0);
        GaussianBlur blur = new GaussianBlur(10); // 55 is just to show edge effect more clearly.
        adj.setInput(blur);
        parent.setEffect(adj);
        parent.setDisable(true);
    }

}
