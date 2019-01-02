package Controllers;

import DB.Borrow;
import DB.Recipient;
import DB.TableData;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sun.awt.geom.AreaOp;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.CalendarDate;
import sun.util.resources.cldr.CalendarData;

import java.net.URL;
import java.sql.*;
import java.util.*;

public class Recipient_Controller implements Initializable {
    String server="localhost",username,ename,Cname,CID,Date= Calendar.getInstance().getTime().toString(),BID,bname,Tdate,Sdate,Address,Phone;
    int port=3306;
    String user="hanin";
    String password="h@n!nabbas123";
    String database="mydb";
    String jdbcurl;
    Connection con=null;
    ArrayList<TableData> borrows = new ArrayList<>();
    @FXML
    Label address,number,emp_name,cname,cid,date;
    @FXML
    TableView<TableData> table;
    @FXML
    TableColumn<TableData,String> bid_col,bname_col,fdate_col,tdate_col;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bid_col.setCellValueFactory(data -> {
            TableData value = data.getValue();
            String string = ""+value.getBid();
            return new ReadOnlyStringWrapper(string);
        });
        bname_col.setCellValueFactory(data -> {
            TableData value = data.getValue();
            return new ReadOnlyStringWrapper(value.getBname());
        });
        fdate_col.setCellValueFactory(data -> {
            TableData value = data.getValue();
            return new ReadOnlyStringWrapper(value.getFdate());
        });
        tdate_col.setCellValueFactory(data -> {
            TableData value = data.getValue();
            return new ReadOnlyStringWrapper(value.getTdate());
        });
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","hanin","h@n!nabbas123" + "" );
            Statement stmt=con.createStatement();
            String get_data = "select * from recepient";
            ResultSet rs = stmt.executeQuery(get_data);
            table.getItems().removeAll(table.getItems());
            while(rs.next()) {
                username = rs.getString(1);
                ename = rs.getString(2);
                CID = rs.getString(3);
                BID = rs.getString(4);
                bname = rs.getString(5);
                Phone = rs.getString(6);
                Address = rs.getString(7);
                Sdate = rs.getString(8);
                Tdate = rs.getString(9);
                borrows.add(new TableData(bname,BID,Sdate,Tdate,Phone,Address));
            }
            rs = stmt.executeQuery("select Cname from Client where Cid="+CID);
            rs.next();
            Cname = rs.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=borrows.size()-1;i>=borrows.size()-BorrowBook_Controller.counter;i--){
            table.getItems().add(borrows.get(i));
        }
        BorrowBook_Controller.setCounter(0);
        emp_name.setText(ename);
        cname.setText(Cname);
        cid.setText(CID);
        date.setText(Date);
        address.setText(Address);
        number.setText(Phone);
    }
    
}
