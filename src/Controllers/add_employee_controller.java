
package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import sun.security.util.Password;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class add_employee_controller implements Initializable {
    String server = "localhost";
    int port = 3306;
    String user = "hanin";
    String password = "h@n!nabbas123";
    String database = "mydb";
    String jdbcurl;
    Connection con = null;
    @FXML
    ChoiceBox<String> choiceBox;
    @FXML
    Label no_UserName, no_Password, no_name, no_birthdate, no_phone, no_Address, no_gender;
    @FXML
    TextField UserName_field, Password_field, name_field, Address_field, phone_field;
    @FXML
    DatePicker date_field;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().addAll("Male", "Female");

    }

    @FXML
    private void handleSumbitEvenet(ActionEvent event) {
        if (checkInfo())
            addEmployee();

    }

    private void addEmployee() {
        String UserName = UserName_field.getText();
        String Password = Password_field.getText();
        String cname = name_field.getText();
        String gender = choiceBox.getValue();
        String address = Address_field.getText();
        String phone = phone_field.getText();
        LocalDate date = date_field.getValue();
        String birthdate = date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "hanin", "h@n!nabbas123" + "");
            Statement stmt = con.createStatement();
            String test = "call addemployee("+"'"+UserName+"',"+"'"+Password+"',"+"'"+cname+"'"+","+"'"+birthdate+"'"+","+"'"+gender+"'"+","+"'"+address+"'"+","+"'"+phone+"','600$')";
            ResultSet rs = stmt.executeQuery(test);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private boolean checkInfo() {
        boolean flag = false;
        if (UserName_field.getText().isEmpty()) {
            no_UserName.setText("Please enter your UserName");
            no_UserName.setTextFill(Color.web("red"));
            flag = false;
        }
            if (name_field.getText().isEmpty()) {
                no_Password.setText("Please enter your Password");
                no_Password.setTextFill(Color.web("red"));
                flag = false;
            }
                if (name_field.getText().isEmpty()) {
                    no_name.setText("Please enter your name");
                    no_name.setTextFill(Color.web("red"));
                    flag = false;
                } else
                    no_name.setText("");
                if (Address_field.getText().isEmpty()) {
                    no_Address.setText("Please enter your email");
                    no_Address.setTextFill(Color.web("red"));
                    flag = false;
                } else
                    no_Address.setText("");
                if (phone_field.getText().isEmpty()) {
                    no_phone.setText("Please enter your phone");
                    no_phone.setTextFill(Color.web("red"));
                    flag = false;
                } else
                    no_phone.setText("");
                if (date_field.getValue() == null) {
                    no_birthdate.setText("Please enter date");
                    no_birthdate.setTextFill(Color.web("red"));
                    flag = false;
                } else
                    no_birthdate.setText("");
                if (choiceBox.getValue() == null) {
                    no_gender.setText("Please enter your gender");
                    no_gender.setTextFill(Color.web("red"));
                    flag = false;
                } else
                    no_gender.setText("");
                if (choiceBox.getValue() != null && date_field.getValue() != null && !phone_field.getText().isEmpty() && !Address_field.getText().isEmpty() && !name_field.getText().isEmpty() && !UserName_field.getText().isEmpty() && !Password_field.getText().isEmpty())
                    ;

                flag = true;
                return flag;
            }

}
