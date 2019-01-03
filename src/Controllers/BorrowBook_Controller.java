package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;

public class BorrowBook_Controller implements Initializable {
    public static int counter = 0;
    String server="localhost";
    int port=3306;
    String user="hanin";
    String password="h@n!nabbas123";
    String database="mydb";
    String jdbcurl;
    Connection con=null;
    @FXML
    AnchorPane container;
    @FXML
    Label no_cid,no_bid,no_date,no_id;
    @FXML
    TextField cid_field,bid_field,Id_field;
    @FXML
    DatePicker sdate,edate;
    @FXML
    Button recipient;
    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    @FXML
    private void handleSubmitEvent(ActionEvent event){
        if(checkInfo()) {
            if(addOrder())
            recipient.setVisible(true);
        }
    }

    private boolean addOrder() {
        String id = Id_field.getText();
        String cid = cid_field.getText();
        String bid = bid_field.getText();
        String stdate=""+sdate.getValue().getYear()+"-"+sdate.getValue().getMonthValue()+"-"+sdate.getValue().getDayOfMonth();
        String endate=""+edate.getValue().getYear()+"-"+edate.getValue().getMonthValue()+"-"+edate.getValue().getDayOfMonth();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "hanin", "h@n!nabbas123" + "");
            Statement stmt = con.createStatement();
            String addBorrow="call addBorrows('"+stdate+"','"+endate+"',"+cid+","+bid+",'"+id+"')";
            System.out.println(addBorrow);
            stmt.executeQuery(addBorrow);
            counter ++;
            return true;
        }catch (Exception e){
            no_date.setText("Failed to add");
            no_date.setTextFill(Color.web("red"));
            e.printStackTrace();
            return false;
        }
    }

    private boolean checkInfo() {
        boolean flag=false;
        if(Id_field.getText()==null){
            no_id.setText("Please enter user ID");
            no_id.setTextFill(Color.web("red"));
            flag=false;
        }
        else
            no_id.setText("");

        if(cid_field.getText().isEmpty()){
            no_cid.setText("Please enter user ID");
            no_cid.setTextFill(Color.web("red"));
            flag=false;
        }
        else
            no_cid.setText("");
        if(bid_field.getText().isEmpty()){
            no_bid.setText("Please enter book ID");
            no_bid.setTextFill(Color.web("red"));
            flag=false;
        }
        else
            no_bid.setText("");
        if(sdate.getValue()==null||edate.getValue()==null){
            no_date.setText("Please enter date");
            no_date.setTextFill(Color.web("red"));
            flag=false;
        }
        else
            no_date.setText("");
        if(sdate.getValue()!=null&&edate.getValue()!=null&&!cid_field.getText().isEmpty()&&!bid_field.getText().isEmpty()&&Id_field.getText()!=null)
            flag = true;
        return flag;
    }

    @FXML
    public void handleRecipientEvent(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml_files/recipient.fxml"));
        stage.setTitle("Recipient");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void setCounter(int counter) {
        BorrowBook_Controller.counter = counter;
    }
}
