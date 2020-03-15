import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Stage;

import java.io.IOException;

public class ContactView {

    public static Parent parent;
    public static ContactViewController controller;
    private static Stage stage;
    private static Scene scene;

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

        stage.setOnCloseRequest(event -> {
            //TODO finir
            terminate();
            SQLConfigWindow.terminate();
            event.consume();
        });

        Object temp = loader.getController();
        controller = (ContactViewController) temp;
        notFocused();
        scene = new Scene(parent);
        stage.setTitle("Contact Manager");
        stage.setScene(scene);
        stage.show();

        controller.getList().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contact>() {
            @Override
            public void changed(ObservableValue<? extends Contact> observable, Contact oldValue, Contact newValue) {
                controller.updateContactDetails(newValue);
                oldValue = newValue;
            }
        });

    }

    public static void isConnected() {
        parent.setEffect(null);
        parent.setDisable(false);
        App.localDatabase = new Database();
        App.localDatabase.startFromRemote();
        controller.getList().setItems(App.localDatabase.getContacts());
    }

    public static void notFocused() {
        ColorAdjust adj = new ColorAdjust(0, 0, -0.05, 0);
        GaussianBlur blur = new GaussianBlur(10); // 55 is just to show edge effect more clearly.
        adj.setInput(blur);
        parent.setEffect(adj);
        parent.setDisable(true);
    }

    public static void isFocused() {
        parent.setEffect(null);
        parent.setDisable(false);
    }

    public static void terminate() {
        stage.hide();
        stage = null;
        parent = null;
        scene = null;
        controller = null;
    }

    public static void refreshListView() {
        controller.getList().refresh();
    }

    public static void updateContactDetails(Contact c) {
        controller.updateContactDetails(c);
    }

    public static void deleteContact(Contact selected) {
        App.localDatabase.deleteContact(selected);
    }

    public static void exportContact(Contact selected) {
        try {
            Vcard.exportContact(stage, selected);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
