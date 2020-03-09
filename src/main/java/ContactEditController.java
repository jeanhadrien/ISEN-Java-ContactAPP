import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.util.Date;

public class ContactEditController {

    @FXML
    private GridPane pane;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    @FXML
    private TextArea address;

    @FXML
    private DatePicker birth;

    @FXML
    private Button confirmButton;

    private Contact selected;

    @FXML
    void confirmButtonPressed(ActionEvent event) {
    }

    public Button getConfirmButton() {
        return this.confirmButton;
    }

    public void loadContact(Contact cont){
        selected = cont;
        firstname.setText(cont.getName().getFirst());
        lastname.setText(cont.getName().getLast());
        phone.setText(cont.getPhone().get());
        email.setText(cont.getEmail().get());
        address.setText(cont.getAddress().get());
        birth.setValue(LocalDate.of(cont.getBirth().getYear(), cont.getBirth().getMonth(), cont.getBirth().getDay()));
    }

    public Contact getNewContact(){
        if(selected != null){ throw new IllegalStateException();}

        Contact cont = new Contact();

        cont.setName(firstname.getText(),lastname.getText());
        cont.setPhone(phone.getText());
        cont.setEmail(email.getText());
        cont.setAddress(address.getText());
        cont.setEmail(email.getText());

        cont.setBirth(Integer.toString(birth.getValue().getYear()),
                Integer.toString(birth.getValue().getMonthValue()),
                Integer.toString(birth.getValue().getDayOfMonth()));

        cont.setId(Database.getNewContactId());
        return cont;

    }


    public void clearFields() {
        selected = null;
        firstname.clear();
        lastname.clear();
        phone.clear();
        email.clear();
        address.clear();
        birth.setValue(null);
    }

    public void updateSelectedContact() {
        selected.setName(firstname.getText(),lastname.getText());
        selected.setPhone(phone.getText());
        selected.setEmail(email.getText());
        selected.setAddress(address.getText());
        selected.setBirth(Integer.toString(birth.getValue().getYear()),
                Integer.toString(birth.getValue().getMonthValue()),
                Integer.toString(birth.getValue().getDayOfMonth()));

    }
}
