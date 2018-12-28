package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class AddClient_Controller implements Initializable {
    @FXML
    ChoiceBox<String> choiceBox;
    @FXML
    Label no_name,no_birthdate,no_phone,no_email,no_gender;
    @FXML
    TextField name_field,email_field,phone_field;
    @FXML
    DatePicker date_field;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().addAll("Male","Female");

    }

    @FXML
    private void handleSumbitEvenet(ActionEvent event){
        checkInfo();

    }

    private void checkInfo() {

        if(name_field.getText().isEmpty()){
            no_name.setText("Please enter your name");
            no_name.setTextFill(Color.web("red"));
        }
        else
            no_name.setText("");
        if(email_field.getText().isEmpty()){
            no_email.setText("Please enter your email");
            no_email.setTextFill(Color.web("red"));
        }
        else
            no_email.setText("");
        if(phone_field.getText().isEmpty()){
            no_phone.setText("Please enter your phone");
            no_phone.setTextFill(Color.web("red"));
        }
        else
            no_phone.setText("");
        if(date_field.getValue()==null){
            no_birthdate.setText("Please enter date");
            no_birthdate.setTextFill(Color.web("red"));
        }
        else
            no_birthdate.setText("");
        if(choiceBox.getValue()==null){
            no_gender.setText("Please enter your gender");
            no_gender.setTextFill(Color.web("red"));
        }
        else
            no_gender.setText("");
    }
}
