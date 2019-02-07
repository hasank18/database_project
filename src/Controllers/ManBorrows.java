package Controllers;

import DB.Books;
import DB.Borrow;
import DB.Client;
import DB.Employee;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ManBorrows implements Initializable {
    Connection con=null;
    private PreparedStatement pst=null;
    @FXML
    AnchorPane container;
    @FXML
    TableView<Borrow> table;
    @FXML
    TableColumn<Borrow,String> borrowId_col,username_col ,sdate_col,tdate_col,BID_col,CID_col,bname_col,cname_col;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String borrowID,username,sdate,tdate,bid,cid,bname,cname;
        borrowId_col.setCellValueFactory(data -> {
            Borrow value = data.getValue();
            String string = ""+value.getBorrowID();
            return new ReadOnlyStringWrapper(string);
        });
        username_col.setCellValueFactory(data -> {
            Borrow value = data.getValue();
            String string = ""+value.getEmployee_user_name();
            return new ReadOnlyStringWrapper(string);
        });
        sdate_col.setCellValueFactory(data -> {
            Borrow value = data.getValue();
            return new ReadOnlyStringWrapper(value.getSdate());
        });
        tdate_col.setCellValueFactory(data -> {
            Borrow value = data.getValue();
            return new ReadOnlyStringWrapper(value.getTdate());
        });
        BID_col.setCellValueFactory(data -> {
            Borrow value = data.getValue();
            return new ReadOnlyStringWrapper(value.getBid());
        });
        CID_col.setCellValueFactory(data -> {
            Borrow value = data.getValue();
            return new ReadOnlyStringWrapper(value.getCid());
        });
        bname_col.setCellValueFactory(data -> {
            Borrow value = data.getValue();
            return new ReadOnlyStringWrapper(value.getBname());
        });
        cname_col.setCellValueFactory(data -> {
            Borrow value = data.getValue();
            return new ReadOnlyStringWrapper(value.getCname());
        });
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","hanin","h@n!nabbas123" + "" );
            Statement stmt=con.createStatement(),stmt2=con.createStatement(),stmt3=con.createStatement();
            String get_data = "select *from Borrows";
            ResultSet rs = stmt.executeQuery(get_data);
            ResultSet rs2,rs3;
            while(rs.next()){
                borrowID = rs.getInt(1)+"";
                sdate = rs.getString(2);
                tdate = rs.getString(3);
                cid = rs.getInt(4)+"";
                bid= rs.getInt(5)+"";
                username = rs.getString(6);
                rs2 = stmt2.executeQuery("select BookName from Books where Book_id="+bid);
                rs2.next();
                bname = rs2.getString(1);
                rs3 = stmt3.executeQuery("select Cname from Client where Cid="+cid);
                rs3.next();
                cname = rs3.getString(1);
                table.getItems().add(new Borrow(borrowID,username,sdate,tdate,bid,bname,cid,cname));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void updateRow(TableColumn.CellEditEvent<Borrow, String> event){
        String borrowId,username,sdate,tdate,bid,bname,cid,cname;
        Borrow borrow = table.getSelectionModel().getSelectedItem();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","hanin","h@n!nabbas123" + "" );
            Statement stmt=con.createStatement(),stmt2=con.createStatement(),stmt3=con.createStatement();
            String get_data = "select *from Borrows where Borrow_id="+borrow.getBorrowID();
            ResultSet rs = stmt.executeQuery(get_data),rs2,rs3;
            while(rs.next()){
                borrowId = rs.getString(1);
                sdate = rs.getString(2);
                tdate = rs.getString(3);
                cid = rs.getString(4);
                bid = rs.getString(5);
                username = rs.getString(6);
                rs2 = stmt2.executeQuery("select BookName from Books where Book_id="+bid);
                rs2.next();
                bname = rs2.getString(1);
                rs3 = stmt3.executeQuery("select Cname from Client where Cid="+cid);
                rs3.next();
                cname = rs3.getString(1);
                table.getSelectionModel().getSelectedItems().set(0,new Borrow(borrowId,username,sdate,tdate,bid,bname,cid,cname));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateUserNameHandler(TableColumn.CellEditEvent<Borrow, String> event) {
        Borrow borrow = table.getSelectionModel().getSelectedItem();
        String edit = "call updateBorrowUsrename("+borrow.getBorrowID()+",'"+event.getNewValue()+"')";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "hanin", "h@n!nabbas123" + "");
            Statement stmt = con.createStatement();
            stmt.executeQuery(edit);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateSdateHandler(TableColumn.CellEditEvent event) {

        Borrow borrow = table.getSelectionModel().getSelectedItem();
        String edit = "call updateBorrowSdate("+borrow.getBorrowID()+",'"+event.getNewValue()+"')";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "hanin", "h@n!nabbas123" + "");
            Statement stmt = con.createStatement();
            stmt.executeQuery(edit);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateTdateHandler(TableColumn.CellEditEvent event) {

        Borrow borrow = table.getSelectionModel().getSelectedItem();
        String edit = "call updateBorrowTdate("+borrow.getBorrowID()+","+event.getNewValue()+")";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "hanin", "h@n!nabbas123" + "");
            Statement stmt = con.createStatement();
            stmt.executeQuery(edit);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateBookIDHandler(TableColumn.CellEditEvent event) {

        Borrow borrow = table.getSelectionModel().getSelectedItem();
        String edit = "call updateBorrowBid("+borrow.getBorrowID()+","+event.getNewValue()+")";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "hanin", "h@n!nabbas123" + "");
            Statement stmt = con.createStatement();
            stmt.executeQuery(edit);
            updateRow(event);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateCIDHandler(TableColumn.CellEditEvent event) {

        Borrow borrow = table.getSelectionModel().getSelectedItem();
        String edit = "call updateBorrowCid("+borrow.getBorrowID()+","+event.getNewValue()+")";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "hanin", "h@n!nabbas123" + "");
            Statement stmt = con.createStatement();
            stmt.executeQuery(edit);
            updateRow(event);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteBorrow(ActionEvent event) {

        Borrow borrow = table.getSelectionModel().getSelectedItem();
        String edit = "delete from Borrows where Borrow_id="+borrow.getBorrowID();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "hanin", "h@n!nabbas123" + "");
            Statement stmt = con.createStatement();
            stmt.executeQuery(edit);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addBorrow(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml_files/borrow_book.fxml"));
        container.getChildren().setAll(pane);
    }

    public void ModifyBorrow(ActionEvent event) {
        table.setEditable(true);
        username_col.setCellFactory(TextFieldTableCell.forTableColumn());
        sdate_col.setCellFactory(TextFieldTableCell.forTableColumn());
        tdate_col.setCellFactory(TextFieldTableCell.forTableColumn());
        BID_col.setCellFactory(TextFieldTableCell.forTableColumn());
        CID_col.setCellFactory(TextFieldTableCell.forTableColumn());
    }
}
