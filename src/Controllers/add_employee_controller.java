package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class add_employee_controller implements Initializable {
    @FXML
    ChoiceBox<String> choiceBox;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().addAll("Male","Female");

    }
}
