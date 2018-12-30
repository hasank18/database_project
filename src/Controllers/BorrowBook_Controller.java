package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;

public class BorrowBook_Controller implements Initializable {
    String server="localhost";
    int port=3306;
    String user="hanin";
    String password="h@n!nabbas123";
    String database="mydb";
    String jdbcurl;
    Connection con=null;
    @FXML
    Label no_cid,no_bid,no_date;
    @FXML
    TextField cid_field,bid_field;
    @FXML
    DatePicker sdate,edate;
    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    @FXML
    private void handleSubmitEvent(ActionEvent event){
        if(checkInfo())
            addOrder();
    }

    private void addOrder() {
        String cid = cid_field.getText();
        String bid = bid_field.getText();
        String stdate=""+sdate.getValue().getYear()+"-"+sdate.getValue().getMonthValue()+"-"+sdate.getValue().getDayOfMonth();
        String endate=""+edate.getValue().getYear()+"-"+edate.getValue().getMonthValue()+"-"+edate.getValue().getDayOfMonth();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "hanin", "h@n!nabbas123" + "");
            Statement stmt = con.createStatement();
            String addBorrow="call addBorrow('"+stdate+"','"+endate+"',"+cid+","+bid+")";
            System.out.println(addBorrow);
            stmt.executeQuery(addBorrow);
        }catch (Exception e){
            no_date.setText("Failed to add");
            no_date.setTextFill(Color.web("red"));
            e.printStackTrace();
        }
    }

    private boolean checkInfo() {
        boolean flag=false;

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
        if(sdate.getValue()!=null&&edate.getValue()!=null&&!cid_field.getText().isEmpty()&&!bid_field.getText().isEmpty())
            flag = true;
        return flag;
    }
}
