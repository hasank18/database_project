package Controllers;

import com.mysql.cj.protocol.ResultsetRow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.DBconnection;
import sun.security.util.Password;

import java.net.URL;
import java.sql.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
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

//    private String getUserName() {
//        String UserName ="";
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","hanin","h@n!nabbas123" + "" );
//            pst = con.prepareStatement("select UserName from Employee where UserName = ?");
//            pst.setString(1, name.getText());
//            rs = pst.executeQuery();
//            if (rs.next())
//                UserName = rs.getString(1);
//            rs.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        System.out.println(UserName);
//        return UserName;
//    }
//
//    private String getPassword() {
//        String Password ="";
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","hanin","h@n!nabbas123" + "" );
//            pst = con.prepareStatement("select Password from Employee where Password = ?");
//            pst.setString(1, pass.getText());
//            rs = pst.executeQuery();
//            if (rs.next())
//                Password = rs.getString(1);
//            rs.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(Password);
//
//
//        return Password;
//    }
    @FXML
private void eventHandler(ActionEvent event) throws Exception {


        Parent parent = FXMLLoader.load(getClass().getResource("../fxml_files/main_page2.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void eventHandler2(ActionEvent event) throws Exception {
        if(checkInfo())
            login();
    }


    private void login () {
            String UserName = name.getText();
            String Password = pass.getText();

            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "hanin", "h@n!nabbas123" + "");
                Statement stmt = con.createStatement();
                ResultSet rs2 = stmt.executeQuery("select UserName,Password  from Employee where UserName= + UserName and Password= + Password");

                if (rs2.next()) {

                    showresult.setText(" login");
                } else {
                    showresult.setText("failed to login");
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
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
