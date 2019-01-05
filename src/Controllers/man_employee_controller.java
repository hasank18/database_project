package Controllers;

import DB.Employee;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class man_employee_controller implements Initializable {
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
    TableView<Employee> table;
    @FXML
    TableColumn<Employee,String> username_col,name_col,date_col,gender_col,address_col,number_col,salary_col,pass_col;
    @FXML
    Button update;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String username,pass,name,birthdate,gender,address,phonenum;
        username_col.setCellValueFactory(data -> {
            Employee value = data.getValue();
            String string = value.getUserName();
            return new ReadOnlyStringWrapper(string);
        });
        pass_col.setCellValueFactory(data -> {
            Employee value = data.getValue();
            String string = value.getPassword();
            return new ReadOnlyStringWrapper(string);
        });
        name_col.setCellValueFactory(data -> {
            Employee value = data.getValue();
            return new ReadOnlyStringWrapper(value.getName());
        });
        date_col.setCellValueFactory(data -> {
            Employee value = data.getValue();
            return new ReadOnlyStringWrapper(value.getBirthdate());
        });
        gender_col.setCellValueFactory(data -> {
            Employee value = data.getValue();
            return new ReadOnlyStringWrapper(value.getGender());
        });
        address_col.setCellValueFactory(data -> {
            Employee value = data.getValue();
            return new ReadOnlyStringWrapper(value.getAddress());
        });
        number_col.setCellValueFactory(data -> {
            Employee value = data.getValue();
            return new ReadOnlyStringWrapper(value.getPhoneNum());
        });
        salary_col.setCellValueFactory(data -> {
            Employee value = data.getValue();
            return new ReadOnlyStringWrapper(value.getSalary());
        });
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","hanin","h@n!nabbas123" + "" );
            Statement stmt=con.createStatement();
            String get_data = "select *from Employee;";
            ResultSet rs = stmt.executeQuery(get_data);
            while(rs.next()){
                username = rs.getString(1);
                pass = rs.getString(2);
                name = rs.getString(3);
                gender = rs.getString(4);
                address = rs.getString(5);
                phonenum = rs.getString(6);
                birthdate = rs.getNString(8);
                table.getItems().add(new Employee(username,pass,name,gender,address,phonenum,birthdate));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void addEmployee(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml_files/add_employee.fxml"));
        container.getChildren().setAll(pane);
    }

    @FXML
    private void editEmployeeInfo(ActionEvent event){
        table.setEditable(true);
        salary_col.setCellFactory(TextFieldTableCell.forTableColumn());
        pass_col.setCellFactory(TextFieldTableCell.forTableColumn());
        name_col.setCellFactory(TextFieldTableCell.forTableColumn());
        date_col.setCellFactory(TextFieldTableCell.forTableColumn());
        gender_col.setCellFactory(TextFieldTableCell.forTableColumn());
        address_col.setCellFactory(TextFieldTableCell.forTableColumn());
        number_col.setCellFactory(TextFieldTableCell.forTableColumn());

    }
    @FXML
    private void updateNameHandler(TableColumn.CellEditEvent event){
        Employee employee = table.getSelectionModel().getSelectedItem();
        String edit = "call updateNameEmployee('"+employee.getUserName()+"','"+event.getNewValue()+"')";
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
    private void updateDateHandler(TableColumn.CellEditEvent event){
        Employee employee = table.getSelectionModel().getSelectedItem();
        String edit = "call updateDateEmployee('"+employee.getUserName()+"','"+event.getNewValue()+"')";
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
    private void updateGenderHandler(TableColumn.CellEditEvent event){
        Employee employee = table.getSelectionModel().getSelectedItem();
        String edit = "call updateGenderEmployee('"+employee.getUserName()+"','"+event.getNewValue()+"')";
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
    private void updateAddressHandler(TableColumn.CellEditEvent event){
        Employee employee = table.getSelectionModel().getSelectedItem();
        String edit = "call updateAddressEmployee('"+employee.getUserName()+"','"+event.getNewValue()+"')";
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
    private void updateSalaryHandler(TableColumn.CellEditEvent event){
        Employee employee = table.getSelectionModel().getSelectedItem();
        String edit = "call updateSalaryEmployee('"+employee.getUserName()+"','"+event.getNewValue()+"')";
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
    private void updatePasswordHandler(TableColumn.CellEditEvent event){
        Employee employee = table.getSelectionModel().getSelectedItem();
        String edit = "call updatePasswordEmployee('"+employee.getUserName()+"','"+event.getNewValue()+"')";
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
    private void updatePhoneHandler(TableColumn.CellEditEvent event){
        Employee employee = table.getSelectionModel().getSelectedItem();
        String edit = "call updatePhoneEmployee('"+employee.getUserName()+"','"+event.getNewValue()+"')";
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
    private void deleteEmployee(ActionEvent event){
        String userName = table.getSelectionModel().getSelectedItem().getUserName();
        String delete_employee = "call deleteEmployee('"+userName+"')";
        table.getItems().remove(table.getSelectionModel().getSelectedItem());
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","hanin","h@n!nabbas123" + "" );
            Statement stmt=con.createStatement();
            stmt.executeQuery(delete_employee);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void update(ActionEvent event){
        String username,pass,name,birthdate,gender,address,phonenum;
        table.getItems().removeAll(table.getItems());
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","hanin","h@n!nabbas123" + "" );
            Statement stmt=con.createStatement();
            String get_data = "select *from Employee";
            ResultSet rs = stmt.executeQuery(get_data);
            while(rs.next()){
                username = rs.getString(1);
                pass = rs.getString(2);
                name = rs.getString(3);
                birthdate = rs.getString(8);
                gender = rs.getString(4);
                address = rs.getString(5);
                phonenum = rs.getString(6);
                table.getItems().add(new Employee(username,pass,name,gender,address,phonenum,birthdate));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
