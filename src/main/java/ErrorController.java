import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ErrorController {

    @FXML
    private GridPane pane;

    @FXML
    public Button button;

    @FXML
    public Label text;

    @FXML
    private void onClick(){
        Error.onClick();
    }

    public void setText(String s){
        text.setText(s);
    }

}
