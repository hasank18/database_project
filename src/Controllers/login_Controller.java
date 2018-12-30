package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.awt.*;
import java.util.ResourceBundle;

public class login_Controller implements Initializable {

    String server = "localhost";
    int port = 3306;
    String user = "root";
    String password = "";
    String database = "mydb";
    String jdbcurl;
    Connection con = null;
    private PreparedStatement pst;
    private ResultSet rs;


    @FXML
    private javafx.scene.control.TextField name;
    @FXML
    private PasswordField pass;
    @FXML
    private Button btnLogin;
    @FXML
    private javafx.scene.control.Label showresult;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private String getUserName() {
        String UserName ="";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","" + "" );
            pst = con.prepareStatement("select UserName from Employee where UserName = ?");
            pst.setString(1, name.getText());
            rs = pst.executeQuery();
            if (rs.next())
                UserName = rs.getString(1);
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
        System.out.println(UserName);
        return UserName;
    }

    private String getPassword() {
        String Password ="";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","" + "" );
            pst = con.prepareStatement("select Password from Employee where Password = ?");
            pst.setString(1, pass.getText());
            rs = pst.executeQuery();
            if (rs.next())
                Password = rs.getString(1);
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Password);
        return Password;
    }


    @FXML
    private void eventHandler(ActionEvent event) throws Exception {
        try {
            if (name.getText().equals(getUserName()) && pass.getText().equals(getPassword())) {

                showresult.setText("welcome");
                Parent parent = FXMLLoader.load(getClass().getResource("../fxml_files/main_page2.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);

                stage.show();

            }
        }catch (Exception e ){
            showresult.setText("failed to login");
        }

    }
}
