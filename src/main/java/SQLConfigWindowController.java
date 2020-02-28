
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class SQLConfigWindowController {

    @FXML
    private TextField serverField;

    @FXML
    private TextField databaseField;

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private GridPane pane;

    @FXML
    private TextField portField;

    @FXML
    private Button connectButton;

    @FXML
    private void connectButtonPressed() {
        SQLConfigWindow.Form form = new SQLConfigWindow.Form(serverField, databaseField, userField, passwordField, portField);
        SQLConfigWindow.gotForm(form);

    }

    public void setFields(SQLConfigWindow.Form form){
        serverField.setText(form.serverField);
        databaseField.setText(form.databaseField);
        userField.setText(form.userField);
        passwordField.setText(form.passwordField);
        portField.setText(form.portField);
    }

    public void clearPassword(){
        passwordField.clear();
    }
    public void disableForm(){
        connectButton.setDisable(true);
    }

    public void enableForm(){
        connectButton.setDisable(false);
    }
}