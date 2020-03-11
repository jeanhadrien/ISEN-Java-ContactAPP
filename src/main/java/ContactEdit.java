import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Stage;

import java.io.IOException;

public class ContactEdit {

    private static Stage stage;
    public static Parent parent;
    private static Scene scene;
    public static ContactEditController controller;

    public static void init() {
        stage = new Stage();
        stage.getIcons().add(App.icon);
        stage.setResizable(false);
        parent = null;
        FXMLLoader loader = null;

        try {
            loader = new FXMLLoader(FileManager.getResourceURL("fxml/ContactEdit.fxml"));
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setOnCloseRequest(event -> {
            stage.hide();
            ContactView.isFocused();
            controller.clearFields();
            event.consume();
        });

        Object temp = loader.getController();
        controller = (ContactEditController) temp;
        scene = new Scene(parent);
        stage.setTitle("Contact Manager");
        stage.setScene(scene);
        stage.hide();

    }


    public static void createNew() {
        ContactView.notFocused();
        stage.show();
        controller.clearFields();

        controller.getConfirmButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    App.localDatabase.addContact(controller.getNewContact());
                }
                catch(IllegalArgumentException e){
                    Error.create("Couldn't create contact, please check your input.",ContactEdit.parent);
                    return;
                }
                ContactView.isFocused();
                stage.hide();

            }
        });
    }

    public static void editContact(Contact selected) {
        ContactView.notFocused();
        stage.show();
        controller.loadContact(selected);

        controller.getConfirmButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.updateSelectedContact();

                App.localDatabase.updateContact(selected);
                ContactView.refreshListView();
                ContactView.updateContactDetails(selected);
                ContactView.isFocused();
                stage.hide();
            }
        });
    }
}
