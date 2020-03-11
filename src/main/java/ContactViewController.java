import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
    private Text firstname;

    @FXML
    private Text lastname;

    @FXML
    private Text phone;

    @FXML
    private Text email;

    @FXML
    private Text address;

    @FXML
    private Text birth;

    @FXML
    private ListView<Contact> list;

    private Contact selected = null;

    @FXML
    private void sqlButtonPressed(){
        ContactView.notFocused();
        SQLConfigWindow.show();
    }

    @FXML
    private void exportButtonPressed(){
        ContactView.exportContact(selected);
    }

    @FXML
    private void deleteButtonPressed(){
        ContactView.deleteContact(selected);
    }

    @FXML
    private void newContactButtonPressed(){
        ContactEdit.createNew();
    }

    @FXML void editContactButtonPressed(){
        if(selected!=null){ ContactEdit.editContact(selected); return;}
    }

    public ListView<Contact> getList(){
        return list;
    }

    public Contact getSelected(){
        return selected;
    }

    public void updateContactDetails(Contact cont){
        firstname.setText(cont.getName().getFirst());
        lastname.setText(cont.getName().getLast());
        phone.setText(cont.getPhone().get());
        email.setText(cont.getEmail().get());
        address.setText(cont.getAddress().get());
        birth.setText(cont.getBirth().get());
        selected = cont;
    }



}
