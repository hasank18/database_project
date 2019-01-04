package Controllers;

import DB.Books;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class man_book_controller implements Initializable {
    String server = "localhost";
    int port = 3306;
    String user = "hanin";
    String password = "h@n!nabbas123";
    String database = "mydb";
    String jdbcurl;
    Connection con = null;
    @FXML
    Label not_found;
    @FXML
    TextField book_name_field;
    @FXML
    AnchorPane container;
    @FXML
    TableView<DB.Books> table;
    @FXML
    TableColumn<DB.Books, String> bookname_col, bookid_col, bookamount_col, bookauth_col, bookcat_col;
    @FXML
    Button update;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String bookname, bookid, amount, auth, cat;
        bookid_col.setCellValueFactory(data -> {
            Books value = data.getValue();
            String string = value.getId();
            return new ReadOnlyStringWrapper(string);
        });
        bookname_col.setCellValueFactory(data -> {
            Books value = data.getValue();
            String string = value.getName();
            return new ReadOnlyStringWrapper(string);
        });
        bookamount_col.setCellValueFactory(data -> {
            Books value = data.getValue();
            return new ReadOnlyStringWrapper(value.getAmout());
        });
        bookauth_col.setCellValueFactory(data -> {
            Books value = data.getValue();
            return new ReadOnlyStringWrapper(((Books) value).getAuth());
        });
        bookcat_col.setCellValueFactory(data -> {
            Books value = data.getValue();
            return new ReadOnlyStringWrapper(value.getCat());
        });
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "hanin", "h@n!nabbas123" + "");
            Statement stmt = con.createStatement();
            String get_data = "select *from Books";
            ResultSet rs = stmt.executeQuery(get_data);
            while (rs.next()) {
                bookid = rs.getString(1);
                bookname = rs.getString(2);
                amount = rs.getString(3);
                auth = rs.getString(4);
                cat = rs.getString(5);
                table.getItems().add(new Books(bookid, bookname, amount, cat, auth));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    {
        TableColumn<Books,String> col1= new TableColumn<>("ID");
        TableColumn<Books,String> col2= new TableColumn<>("Name");
        TableColumn<Books,String> col3= new TableColumn<>("Amount");
        TableColumn<Books,String> col4= new TableColumn<>("Author");
        TableColumn<Books,String> col5= new TableColumn<>("Category");
        String auth = "";
        String cat = "";
        String id="";
        String name="";
        String amount="";
        col1.setCellValueFactory(data -> {
            Books value = data.getValue();
            return new ReadOnlyStringWrapper(value.getId());
        });
        col2.setCellValueFactory(data -> {
            Books value = data.getValue();
            return new ReadOnlyStringWrapper(value.getName());
        });
        col3.setCellValueFactory(data -> {
            Books value = data.getValue();
            return new ReadOnlyStringWrapper(value.getAmout());
        });
        col4.setCellValueFactory(data -> {
            Books value = data.getValue();
            return new ReadOnlyStringWrapper(value.getAuth());
        });
        col5.setCellValueFactory(data -> {
            Books value = data.getValue();
            return new ReadOnlyStringWrapper(value.getCat());
        });
        table.getColumns().removeAll(table.getColumns());
        table.getColumns().addAll(col1,col2,col3,col4,col5);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","hanin","h@n!nabbas123" + "" );
            Statement stmt=con.createStatement();
            String test = "select *from Books where BookName='"+book_name_field.getText()+"'";
            ResultSet rs2 = stmt.executeQuery(test);
            rs2.next();
            id = ""+rs2.getInt(1);
            name = rs2.getString(2);
            amount = ""+rs2.getInt(3);
            int auth_id = rs2.getInt(4);
            int cat_id = rs2.getInt(5);
            String get_auth= "select AuthorName from Author where Author_id="+auth_id;
            String get_cat = "select CategoryName from Category where Category_id="+cat_id;
            rs2 = stmt.executeQuery(get_auth);
            rs2.next();
            auth = rs2.getString(1);
            rs2 = stmt.executeQuery(get_cat);
            rs2.next();
            cat = rs2.getString(1);
        }catch (Exception e) {
            not_found.setText("Book Not Found");
            not_found.setTextFill(Color.web("red"));

        }

        table.getItems().add(new Books(id,name,amount,auth,cat));
        //added nothing
    }




    @FXML
    private void addBook(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml_files/add_book.fxml"));
        container.getChildren().setAll(pane);
    }
    @FXML
    private void editBookinfo(ActionEvent event){
        table.setEditable(true);
        bookid_col.setCellFactory(TextFieldTableCell.forTableColumn());
        bookname_col.setCellFactory(TextFieldTableCell.forTableColumn());
        bookamount_col.setCellFactory(TextFieldTableCell.forTableColumn());
        bookcat_col.setCellFactory(TextFieldTableCell.forTableColumn());
        bookauth_col.setCellFactory(TextFieldTableCell.forTableColumn());
        update.setVisible(true);
    }
    @FXML
    private void updateNameHandler(TableColumn.CellEditEvent event){
        Books Books = table.getSelectionModel().getSelectedItem();
        String edit = "call updateBookName('"+Books.getId()+"','"+Books.getName()+"','"+event.getNewValue()+"')";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "hanin", "h@n!nabbas123" + "");
            Statement stmt = con.createStatement();
            stmt.executeQuery(edit);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void updateAmountHandler(TableColumn.CellEditEvent event){
        Books Books = table.getSelectionModel().getSelectedItem();
        String edit = "call updatebookamount('"+Books.getId()+"','"+event.getNewValue()+"')";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "hanin", "h@n!nabbas123" + "");
            Statement stmt = con.createStatement();
            stmt.executeQuery(edit);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void updateAuthorHandler(TableColumn.CellEditEvent event){
        Books Books = table.getSelectionModel().getSelectedItem();
        String edit = "call updatebookauthor('"+Books.getId()+"','"+event.getNewValue()+"')";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "hanin", "h@n!nabbas123" + "");
            Statement stmt = con.createStatement();
            stmt.executeQuery(edit);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void updateCategoryHandler(TableColumn.CellEditEvent event){
       Books Books = table.getSelectionModel().getSelectedItem();
        String edit = "call updatebookcategory('"+Books.getId()+"','"+event.getNewValue()+"')";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "hanin", "h@n!nabbas123" + "");
            Statement stmt = con.createStatement();
            stmt.executeQuery(edit);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void deleteBook(ActionEvent event){
        String id = table.getSelectionModel().getSelectedItem().getId();
        String delete_Book = "call delbook('"+id+ "')";
        table.getItems().remove(table.getSelectionModel().getSelectedItem());
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","hanin","h@n!nabbas123" + "" );
            Statement stmt=con.createStatement();
            stmt.executeQuery(delete_Book);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void update(ActionEvent event){
        String bookid,bookname,amount,author,category;
        table.getItems().removeAll(table.getItems());
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","hanin","h@n!nabbas123" + "" );
            Statement stmt=con.createStatement();
            String get_data = "select *from Books";
            ResultSet rs = stmt.executeQuery(get_data);
            while(rs.next()){
                bookid = rs.getString(1);
                bookname = rs.getString(2);
                amount = rs.getString(3);
                category = rs.getString(4);
                author = rs.getString(5);

                table.getItems().add(new Books(bookid,bookname,amount,category,author));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}