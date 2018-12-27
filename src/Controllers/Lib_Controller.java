package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Lib_Controller implements Initializable {
    @FXML
    ChoiceBox<String> category;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FillChoiceBox();
    }

    @FXML
    private void FillChoiceBox(){
        category.getItems().addAll("Category","Date","Amount");
    }
}
