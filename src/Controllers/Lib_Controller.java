package Controllers;

import DB.Books;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Lib_Controller implements Initializable {
    private String server="localhost";
    private int port=3306;
    private String user="hanin";
    private String password="h@n!nabbas123";
    private String database="mydb";
    private String jdbcurl;
    private Connection con=null;
    private Label label = new Label();
    private TextField textField = new TextField();
    private DatePicker datePicker = new DatePicker();
    private ChoiceBox<String> choiceBox = new ChoiceBox<String>();
    @FXML
    TableView<Books> table;
    @FXML
    TextField book_name_field;
    @FXML
    HBox container;
    @FXML
    ComboBox<String> category;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FillChoiceBox();
    }

    @FXML
    private void FillChoiceBox(){
        category.getItems().addAll("Category","Date","Amount");
    }
    @FXML
    private void displayValue(ActionEvent event){
        if(!container.getChildren().isEmpty()) {
            container.getChildren().removeAll(container.getChildren());
        }
        String choice = category.getValue();
        if(choice.equals("Category"))
            ChooseCategory();
        else if(choice.equals("Date"))
            ChooseDate();
        else if(choice.equals("Amount")){
            ChooseAmount();
        }
    }

    private void ChooseAmount() {
        label.setText("Choose amount");
        textField.setPromptText("if this field is empty all amounts will be shown with book's name only");
        textField.setPrefWidth(470);
        container.getChildren().addAll(label,textField);
        container.setMargin(label,new Insets(0,8,0,0));
        container.setMargin(textField,new Insets(0,0,0,8));
    }

    private void ChooseDate() {
        label.setText("Choose date");
        container.getChildren().addAll(label,datePicker);
        container.setMargin(label,new Insets(0,8,0,0));
        container.setMargin(datePicker,new Insets(0,0,0,8));
    }

    private void ChooseCategory() {
        label.setText("Choose Category");
        choiceBox.getItems().addAll("Action","Comedy","Drama","Education","Horror","Romance","Thriller");
        container.getChildren().addAll(label,choiceBox);
        container.setMargin(label,new Insets(0,8,0,0));
        container.setMargin(choiceBox,new Insets(0,0,0,8));

    }
    @FXML
    private void handleSearchEvent(ActionEvent event){
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        table.getItems().add(new Books(id,name,amount,auth,cat));
        //added nothing
    }
    @FXML
    private void handleFilterEvent(ActionEvent event){
        if(category.getValue()==null){
            Label label= new Label("please choose an option");
            label.setTextFill(Color.web("red"));
            container.getChildren().removeAll(container.getChildren());
            container.getChildren().add(label);
        }
        else if(category.getValue().equals("Date"))
            search_date();
        else if(category.getValue().equals("Amount")){
            search_amount();
        }
        else{
            search_category();
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
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","" + "" );
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void search_date() {
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
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","" + "" );
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

}