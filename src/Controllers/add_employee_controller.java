
package Controllers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.*;
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
        no_UserName.setText("");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "hanin", "h@n!nabbas123" + "");
            Statement stmt = con.createStatement();
            String test = "call addemployee("+"'"+UserName+"',"+"'"+Password+"',"+"'"+cname+"',"+"'"+gender+"','"+address+"','"+phone+"','600$','"+birthdate+"')";
            ResultSet rs = stmt.executeQuery(test);
        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            no_UserName.setText("User name already taken");
            no_UserName.setTextFill(Color.web("red"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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
            if (Password_field.getText().isEmpty()) {
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
                    flag = true;
                return flag;
            }

}
