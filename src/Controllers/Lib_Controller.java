package Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Double.MAX_VALUE;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Lib_Controller implements Initializable {
    @FXML
    HBox container;
    @FXML
    ComboBox<String> category;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FillChoiceBox();
    }

    @FXML
    private void FillChoiceBox(){
        category.getItems().addAll("Category","Date","Amount");
    }
    @FXML
    private void displayValue(ActionEvent event){
        if(!container.getChildren().isEmpty()) {
            container.getChildren().removeAll(container.getChildren());
        }
        String choice = category.getValue();
        if(choice.equals("Category"))
            ChooseCategory();
        else if(choice.equals("Date"))
            ChooseDate();
        else if(choice.equals("Amount")){
            ChooseAmount();
        }
    }

    private void ChooseAmount() {
        Label label = new Label("Choose the Date");
        TextField textField = new TextField();
        textField.setPromptText("if this field is empty all amounts will be shown with book's name only");
        textField.setPrefWidth(470);
        container.getChildren().addAll(label,textField);
        container.setMargin(label,new Insets(0,8,0,0));
        container.setMargin(textField,new Insets(0,0,0,8));
    }

    private void ChooseDate() {
        Label label = new Label("Choose the Date");
        DatePicker datePicker = new DatePicker();
        container.getChildren().addAll(label,datePicker);
        container.setMargin(label,new Insets(0,8,0,0));
        container.setMargin(datePicker,new Insets(0,0,0,8));
    }

    private void ChooseCategory() {
        Label label = new Label("Choose the Category");
        ChoiceBox<String> choiceBox = new ChoiceBox<String>();
        choiceBox.getItems().addAll("Thriller","Romance","Drama","Comedy");
        container.getChildren().addAll(label,choiceBox);
        container.setMargin(label,new Insets(0,8,0,0));
        container.setMargin(choiceBox,new Insets(0,0,0,8));

    }
}
