import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/*
 * GUI : Manage SQL Configuration Window
 */
public class SQLConfigWindow {

    /*
     * A form contains raw String data entered by user for the SQL connection
     */
    public static class Form {

        Form(TextField serv, TextField db, TextField usr, PasswordField pw, TextField port) {
            this.serverField = serv.getText();
            this.databaseField = db.getText();
            this.userField = usr.getText();
            this.passwordField = pw.getText();
            this.portField = port.getText();
        }

        public String serverField;
        public String databaseField;
        public String userField;
        public String passwordField;
        public String portField;

        @Override
        public String toString() {
            return "serverField:" + serverField +
                    "\ndatabaseField:" + databaseField +
                    "\nuserField:" + userField +
                    "\npasswordField:" + passwordField +
                    "\nportField:" + portField;
        }
    }

    private static Stage stage;
    public static Parent parent;
    private static Scene scene;
    public static SQLConfigWindowController controller;

    public static void init() {
        stage = new Stage();
        stage.getIcons().add(App.icon);
        stage.setResizable(false);
        parent = null;
        FXMLLoader loader = null;

        try {
            loader = new FXMLLoader(FileManager.getResourceURL("fxml/SQLConfigWindow.fxml"));
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setOnCloseRequest(event -> {
                event.consume();
        });

        Object temp = loader.getController();
        controller = (SQLConfigWindowController) temp;
        scene = new Scene(parent);
        stage.setTitle("SQL Config");
        stage.setScene(scene);
        stage.show();
    }

    public static void gotForm(Form form) {
        controller.disableForm();

        if (matchesRegex(form)) {
            if (Sql.tryToConnect(form)) {
                ContactView.isConnected();
                stage.hide();
                controller.clearPassword();
                controller.enableForm();
            } else {
                System.out.println("xd");
                controller.enableForm();
            }
        } else {
            //TODO form not matching regex
        }
        controller.enableForm();

    }

    public static void show(){
        stage.show();
    }
    public static void clearPassword(){
        controller.clearPassword();
    }

    public static boolean matchesRegex(Form form) {
		/* System.out.println(form);
		System.out.println(form.databaseField.matches(Regex.BASIC));
		System.out.println(form.portField.matches(Regex.NUMBER_WHOLE));
		System.out.println((form.serverField.matches(Regex.IPV4) || form.serverField=="localhost"));
		System.out.println(form.userField.matches(Regex.USERNAME)); */

        if (form.databaseField.matches(Regex.BASIC)
                && form.portField.matches(Regex.NUMBER_WHOLE)
                && (form.serverField.matches(Regex.IPV4) || form.serverField.equals("localhost"))
                && form.userField.matches(Regex.USERNAME)) {
            return true;
        } else return false;
    }

    public static void terminate(){
        stage.hide();
        stage = null;
        parent = null;
        scene = null;
        controller = null;
    }


}
