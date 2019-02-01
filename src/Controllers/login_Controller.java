package Controllers;


import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.*;
import java.awt.*;
import java.sql.Connection;
import java.util.ResourceBundle;


public class login_Controller implements Initializable {

    String server = "localhost";
    int port = 3306;
    String user = "hanin";
    String password = "h@n!nabbas123";
    String database = "mydb";
    String jdbcurl;
    Connection con = null;
    private PreparedStatement pst;
    private ResultSet rs;



   @FXML
   private Button Btn;
    @FXML
    private TextField name;
    @FXML
    private PasswordField pass;
    @FXML
    private Button btnLogin;
    @FXML
    private Label showresult;
    @FXML
    private Label no_UserName;
    @FXML
    private Label no_Password;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
    @FXML
    private void eventHandler(ActionEvent event)  throws Exception {
        if (checkInfo() && login()) {
            Parent parent = FXMLLoader.load(getClass().getResource("../fxml_files/main_page2.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
            if (name.getText().equals("admin") && pass.getText().equals("admin")) {
                Parent parent = FXMLLoader.load(getClass().getResource("../fxml_files/main_page.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }

        }


//    private void enterpass(java.awt.event.KeyEvent evt)  throws Exception {
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            login();
//        }
//
//    }

    private boolean login () {
        String UserName = name.getText();
        String Password = pass.getText();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "hanin", "h@n!nabbas123" + "");
            Statement stmt = con.createStatement();
            String data = "select UserName,Password  from Employee where UserName='" + UserName + "'and Password='" + Password + "'";
            ResultSet rs2 = stmt.executeQuery(data);
            if (rs2.next()) {
                return true;
            } else{
                showresult.setText("failed to login");
            return false;
        }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
    private boolean checkInfo() {
        boolean flag = false;
        if (name.getText().isEmpty()) {
            no_UserName.setText("Please enter your UserName");
            no_UserName.setTextFill(javafx.scene.paint.Color.web("red"));
            flag = false;
        }
        if (pass.getText().isEmpty()) {
            no_Password.setText("Please enter your Password");
            no_Password.setTextFill(Color.web("red"));
            flag = false;
        }
        if (!name.getText().isEmpty() && !pass.getText().isEmpty() )
            flag = true;
        return flag;
    }

}
