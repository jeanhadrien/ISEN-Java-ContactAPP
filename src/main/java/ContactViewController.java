import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ContactViewController {

    @FXML
    private Button sql;

    @FXML
    private Button newContact;

    @FXML
    private Button edit;

    @FXML
    private Button delete;

    @FXML
    private Button export;

    @FXML
    private VBox contactDetails;

    @FXML
    private HBox contactNameHBox;

    @FXML
    private Text Firstname;

    @FXML
    private Text Lastname;

    @FXML
    private Text phone;

    @FXML
    private Text email;

    @FXML
    private Text address;

    @FXML
    private Text birth;

    @FXML
    private ListView<?> list;

    @FXML
    private void SQLButtonHandle(){
        ContactView.isNotConnected();
        SQLConfigWindow.show();
    }

}
