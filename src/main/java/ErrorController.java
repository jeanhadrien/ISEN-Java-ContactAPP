import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ErrorController {

    @FXML
    public Button button;
    @FXML
    public Label text;
    @FXML
    private GridPane pane;

    @FXML
    private void onClick() {
        Error.onClick();
    }

    public void setText(String s) {
        text.setText(s);
    }

}
