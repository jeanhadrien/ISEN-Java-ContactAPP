import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Stage;

import java.io.IOException;

public class Error {

    private static Stage stage;
    private static Parent parent;
    private static Scene scene;
    private static ErrorController controller;

    public static void init() {
        stage = new Stage();
        stage.getIcons().add(App.icon);
        stage.hide();
        parent = null;
        FXMLLoader loader = null;

        try {
            loader = new FXMLLoader(FileManager.getResourceURL("fxml/Error.fxml"));
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        controller = loader.getController();
        scene = new Scene(parent);

        stage.setTitle("Notification");
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.setScene(scene);

    }


    public static void create(String error, String solution, Parent origin) {

        ColorAdjust adj = new ColorAdjust(0, 0, -0.05, 0);
        GaussianBlur blur = new GaussianBlur(10); // 55 is just to show edge effect more clearly.
        adj.setInput(blur);
        origin.setEffect(adj);
        origin.setDisable(true);

        controller.button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                origin.setEffect(null);
                origin.setDisable(false);
                stage.hide();
            }
        });

        controller.setText("ERROR :  " + error + "\n\nSOLUTION :  " + solution + "\n");
        controller.text.setWrapText(true);
        stage.sizeToScene();
        stage.show();
    }

    public static void create(String notif, Parent origin) {
        ColorAdjust adj = new ColorAdjust(0, 0, -0.05, 0);
        GaussianBlur blur = new GaussianBlur(10); // 55 is just to show edge effect more clearly.
        adj.setInput(blur);
        origin.setEffect(adj);
        origin.setDisable(true);

        controller.button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                origin.setEffect(null);
                origin.setDisable(false);
                stage.hide();
            }
        });

        controller.setText(notif);
        controller.text.setWrapText(true);
        stage.sizeToScene();
        stage.show();
    }

    public static void onClick() {
        stage.hide();
    }
}
