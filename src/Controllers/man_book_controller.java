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
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class man_book_controller implements Initializable {
    String server = "localhost";
    int port = 3306;
    String user = "hanin";
    String password = "h@n!nabbas123";
    String database = "mydb";
    String jdbcurl;
    Connection con = null;
    private Label label = new Label();
    private TextField textField = new TextField();
    private PreparedStatement pst = null;
    private ChoiceBox<String> choiceBox = new ChoiceBox<String>();
    @FXML
    Label not_found;
    @FXML
    TextField book_name_field,search;
    @FXML
    AnchorPane container;
    @FXML
    TableView<DB.Books> table;
    @FXML
    TableColumn<DB.Books, String> bookname_col, bookid_col, bookamount_col, bookauth_col, bookcat_col;
    @FXML
    Button update;
    @FXML
    HBox container2;
    @FXML
    ComboBox<String> category;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

       FillChoiceBox();

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
            String get_data = "select *from Books ORDER BY BookName";
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

    @FXML
    private void FillChoiceBox(){
        category.getItems().addAll("Category","Amount");
    }
    @FXML
    private void displayValue(ActionEvent event){
        if(!container2.getChildren().isEmpty()) {
            container2.getChildren().removeAll(container2.getChildren());
        }
        String choice = category.getValue();
        if(choice.equals("Category"))
            ChooseCategory();

        else if(choice.equals("Amount")){
            ChooseAmount();
        }
    }

    private void ChooseAmount() {
        label.setText("Choose amount");
        textField.setPromptText("if this field is empty all amounts will be shown with book's name only");
        textField.setPrefWidth(470);
        container2.getChildren().addAll(label,textField);
        container2.setMargin(label,new Insets(0,8,0,0));
        container2.setMargin(textField,new Insets(0,0,0,8));
    }



    private void ChooseCategory() {
        label.setText("Choose Category");
        choiceBox.getItems().addAll("Action","Comedy","Drama","Education","Horror","Romance","Thriller");
        container2.getChildren().addAll(label,choiceBox);
        container2.setMargin(label,new Insets(0,8,0,0));
        container2.setMargin(choiceBox,new Insets(0,0,0,8));

    }


    @FXML
    private void searchbook() {
        if (search.getText().equals("")) {
            table.getItems().clear();
            try {
                pst = con.prepareStatement("select *from Books");
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    table.getItems().add(new Books (rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
                }
            } catch (SQLException e1) {
                Logger.getLogger(man_book_controller.class.getName()).log(Level.SEVERE ,null,e1);
            }
        }
        else {
            table.getItems().clear();
            String sql = "select * from Books where BookName like '"  + search.getText() + "%'";
            try {
                pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    String auth = rs.getString(3);
                    String cat = rs.getString(4);
                    String amount=rs.getString(5);
                    table.getItems().add(new Books (id,name,amount,cat,auth));
                }
            } catch (SQLException e1) {
                Logger.getLogger(man_book_controller.class.getName()).log(Level.SEVERE, null, e1);
            }

        }
    }
    @FXML
    private void handleFilterEvent(ActionEvent event){
        table.getItems().removeAll(table.getItems());
        if(category.getValue()==null){
            Label label= new Label("please choose an option");
            label.setTextFill(Color.web("red"));
            container2.getChildren().removeAll(container2.getChildren());
            container2.getChildren().add(label);
        }
        else if(category.getValue().equals("Amount")){
            search_amount();
        }
        else{
            if( category.getValue().equals("Category")){
                search_category();
            }
        }
    }

    private void search_amount() {
        TableColumn<Books,String> col1= new TableColumn<>("ID");
        TableColumn<Books,String> col2= new TableColumn<>("Name");
        TableColumn<Books,String> col3= new TableColumn<>("Amount");
        TableColumn<Books,String> col4= new TableColumn<>("Author");
        TableColumn<Books,String> col5= new TableColumn<>("Category");
        ArrayList<String> auth = new ArrayList<>();
        ArrayList<String> cat = new ArrayList<>();
        ArrayList<String> id= new ArrayList<>();
        ArrayList<String> name=new ArrayList<>();
        ArrayList<String> amount=new ArrayList<>();
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
        String string = ""+textField.getText();
        int amount2 = Integer.parseInt(string);
        String get_data = "select *from Books where Amount="+amount2;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","hanin","h@n!nabbas123" + "" );
            Statement stmt=con.createStatement();
            ResultSet rs2 = stmt.executeQuery(get_data);
            ArrayList<Integer> auth_id = new ArrayList<>();
            ArrayList<Integer> cat_id = new ArrayList<>();
            while (rs2.next()) {
                id.add("" + rs2.getInt(1));
                name.add(rs2.getString(2));
                amount.add("" + rs2.getInt(3));
                auth_id.add(rs2.getInt(4));
                cat_id.add(rs2.getInt(5));
            }
            ResultSet rs3;
            for(int i =0; i < auth_id.size();i++) {
                String get_auth = "select AuthorName from Author where Author_id=" + auth_id.get(i);
                String get_cat = "select CategoryName from Category where Category_id=" + cat_id.get(i);
                rs3 = stmt.executeQuery(get_auth);
                rs3.next();
                auth.add(rs3.getString(1));
                rs3 = stmt.executeQuery(get_cat);
                rs3.next();
                cat.add(rs3.getString(1));
                table.getItems().add(new Books(id.get(i), name.get(i), amount.get(i), auth.get(i), cat.get(i)));
            }
        }catch (Exception e) {
            // TODO Auto-generated catch block            e.printStackTrace();
        }
    }



    private void search_category() {
        TableColumn<Books,String> col1= new TableColumn<>("ID");
        TableColumn<Books,String> col2= new TableColumn<>("Name");
        TableColumn<Books,String> col3= new TableColumn<>("Amount");
        TableColumn<Books,String> col4= new TableColumn<>("Author");
        TableColumn<Books,String> col5= new TableColumn<>("Category");
        ArrayList<String> auth = new ArrayList<>();
        ArrayList<String> cat = new ArrayList<>();
        ArrayList<String> id= new ArrayList<>();
        ArrayList<String> name=new ArrayList<>();
        ArrayList<String> amount=new ArrayList<>();
        ArrayList<Integer> auth_id = new ArrayList<>();
        ArrayList<Integer> cat_id = new ArrayList<>();
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
        table.getItems().removeAll(table.getItems());
        table.getColumns().addAll(col1,col2,col3,col4,col5);
        String get_cat_id="select Category_id from Category where CategoryName='"+choiceBox.getValue()+"'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","hanin","h@n!nabbas123" + "" );
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery(get_cat_id);
            rs.next();
            String get_data = "select * from Books where Category_Category_id1="+rs.getInt(1);
            ResultSet rs2 = stmt.executeQuery(get_data);
            while (rs2.next()) {
                id.add("" + rs2.getInt(1));
                name.add(rs2.getString(2));
                amount.add("" + rs2.getInt(3));
                auth_id.add(rs2.getInt(4));
                cat_id.add(rs2.getInt(5));
            }
            ResultSet rs3;
            for(int i =0; i < auth_id.size();i++) {
                String get_auth = "select AuthorName from Author where Author_id=" + auth_id.get(i);
                String get_cat = "select CategoryName from Category where Category_id=" + cat_id.get(i);
                rs3 = stmt.executeQuery(get_auth);
                rs3.next();
                auth.add(rs3.getString(1));
                rs3 = stmt.executeQuery(get_cat);
                rs3.next();
                cat.add(rs3.getString(1));
                table.getItems().add(new Books(id.get(i), name.get(i), amount.get(i), auth.get(i), cat.get(i)));
            }
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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

    }

    @FXML
    private void updateNameHandler(TableColumn.CellEditEvent event){
        Books Books = table.getSelectionModel().getSelectedItem();
        String edit = "call updateBookName('"+Books.getId()+"','"+event.getNewValue()+"')";
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
//        String delete_Book = "call delbook('"+id+ "')";
        table.getItems().remove(table.getSelectionModel().getSelectedItem());
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","hanin","h@n!nabbas123" + "" );
            Statement stmt=con.createStatement();
            stmt.executeQuery("delete from books where books.Book_id"+id+"");
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