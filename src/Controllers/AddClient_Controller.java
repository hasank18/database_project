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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddClient_Controller implements Initializable {
    String server="localhost";
    int port=3306;
    String user="hanin";
    String password="h@n!nabbas123";
    String database="mydb";
    String jdbcurl;
    Connection con=null;
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
        if(checkInfo())
            addClient();

    }

    private void addClient() {
        String cname=name_field.getText();
        String gender=choiceBox.getValue();
        String address=email_field.getText();
        String phone=phone_field.getText();
        LocalDate date=date_field.getValue();
        String birthdate = date.getYear()+"-"+date.getMonthValue()+"-"+date.getDayOfMonth();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","h@n!nabbas123" + "" );
            Statement stmt=con.createStatement();
            String test ="call addclient("+"'"+cname+"'"+","+"'"+birthdate+"'"+","+"'"+gender+"'"+","+"'"+address+"'"+","+"'"+phone+"')";
            ResultSet rs=stmt.executeQuery(test);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    private boolean checkInfo() {
        boolean flag=false;

        if(name_field.getText().isEmpty()){
            no_name.setText("Please enter your name");
            no_name.setTextFill(Color.web("red"));
            flag=false;
        }
        else
            no_name.setText("");
        if(email_field.getText().isEmpty()){
            no_email.setText("Please enter your email");
            no_email.setTextFill(Color.web("red"));
            flag=false;
        }
        else
            no_email.setText("");
        if(phone_field.getText().isEmpty()){
            no_phone.setText("Please enter your phone");
            no_phone.setTextFill(Color.web("red"));
            flag=false;
        }
        else
            no_phone.setText("");
        if(date_field.getValue()==null){
            no_birthdate.setText("Please enter date");
            no_birthdate.setTextFill(Color.web("red"));
            flag=false;
        }
        else
            no_birthdate.setText("");
        if(choiceBox.getValue()==null){
            no_gender.setText("Please enter your gender");
            no_gender.setTextFill(Color.web("red"));
            flag=false;
        }
        else
            no_gender.setText("");
        if(choiceBox.getValue()!=null&&date_field.getValue()!=null&&!phone_field.getText().isEmpty()&&!email_field.getText().isEmpty()&&!name_field.getText().isEmpty())
            flag = true;
        return flag;
    }
}
