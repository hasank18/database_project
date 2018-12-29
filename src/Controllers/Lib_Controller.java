package Controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class Lib_Controller implements Initializable {
    String server="localhost";
    int port=3306;
    String user="root";
    String password="";
    String database="mydb";
    String jdbcurl;
    Connection con=null;
    @FXML
    TableView<String> table;
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
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","" + "" );
            Statement stmt=con.createStatement();
            String test = "select *from Books where BookName='"+book_name_field.getText()+"'";
            ResultSet rs2 = stmt.executeQuery(test);
            rs2.next();
            System.out.println(rs2.getFetchSize());
            System.out.println(rs2.getInt(1));
            table.getColumns().removeAll(table.getColumns());
            TableColumn<String,String> col1= new TableColumn<String, String>("ID");
            col1.setCellValueFactory(data -> {
                String Value = data.getValue();
                return new ReadOnlyStringWrapper(Value);
            });
            table.getColumns().add(col1);
            String string = ""+rs2.getInt(1);
            table.getItems().addAll(string,string,string,"hasan");



        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }
}
