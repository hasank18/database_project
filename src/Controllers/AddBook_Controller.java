package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class AddBook_Controller implements Initializable {
    @FXML
    Label no_name,no_amount,no_category,no_author;
    @FXML
    TextField name_field,amount_field,category_field,author_field;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
        if(amount_field.getText().isEmpty()){
            no_amount.setText("Please enter your email");
            no_amount.setTextFill(Color.web("red"));
        }
        else
            no_amount.setText("");
        if(category_field.getText().isEmpty()){
            no_category.setText("Please enter your phone");
            no_category.setTextFill(Color.web("red"));
        }
        else
            no_category.setText("");
        if(author_field.getText().isEmpty()){
            no_author.setText("Please enter date");
            no_author.setTextFill(Color.web("red"));
        }
        else
            no_author.setText("");
    }

}
