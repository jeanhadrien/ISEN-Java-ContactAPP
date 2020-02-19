
import javafx.fxml.FXML;
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
    private void connectButtonPressed() {
    	SQLConfigWindow.Form form = new SQLConfigWindow.Form(serverField, databaseField, userField, passwordField, portField); 
    	SQLConfigWindow.getForm(form);
    }

}