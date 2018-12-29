package Controllers;

import DB.Books;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.LongStream;

public class Lib_Controller implements Initializable {
    String server="localhost";
    int port=3306;
    String user="root";
    String password="";
    String database="mydb";
    String jdbcurl;
    Connection con=null;
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
        Label label = new Label("Choose the Date");
        TextField textField = new TextField();
        textField.setPromptText("if this field is empty all amounts will be shown with book's name only");
        textField.setPrefWidth(470);
        container.getChildren().addAll(label,textField);
        container.setMargin(label,new Insets(0,8,0,0));
        container.setMargin(textField,new Insets(0,0,0,8));
    }

    private void ChooseDate() {
        Label label = new Label("Choose the Date");
        DatePicker datePicker = new DatePicker();
        container.getChildren().addAll(label,datePicker);
        container.setMargin(label,new Insets(0,8,0,0));
        container.setMargin(datePicker,new Insets(0,0,0,8));
    }

    private void ChooseCategory() {
        Label label = new Label("Choose the Category");
        ChoiceBox<String> choiceBox = new ChoiceBox<String>();
        choiceBox.getItems().addAll("Thriller","Romance","Drama","Comedy");
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
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","" + "" );
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
}
