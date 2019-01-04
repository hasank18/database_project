package Controllers;

import DB.Books;
import DB.Client;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class man_client_controller implements Initializable {
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
    TableView<Client> table;
    @FXML
    TableColumn<Client,String> cid_col,name_col,date_col,gender_col,address_col,number_col;
    @FXML
    Button update;
    @FXML
    TextField search_field;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        int cid;
        String cname,birthdate,gender,address,phonenum;
        cid_col.setCellValueFactory(data -> {
            Client value = data.getValue();
            String string = ""+value.getCid();
            return new ReadOnlyStringWrapper(string);
        });
        name_col.setCellValueFactory(data -> {
            Client value = data.getValue();
            return new ReadOnlyStringWrapper(value.getCname());
        });
        date_col.setCellValueFactory(data -> {
            Client value = data.getValue();
            return new ReadOnlyStringWrapper(value.getBirthdate());
        });
        gender_col.setCellValueFactory(data -> {
            Client value = data.getValue();
            return new ReadOnlyStringWrapper(value.getGander());
        });
        address_col.setCellValueFactory(data -> {
            Client value = data.getValue();
            return new ReadOnlyStringWrapper(value.getAddress());
        });
        number_col.setCellValueFactory(data -> {
            Client value = data.getValue();
            return new ReadOnlyStringWrapper(value.getPhonenum());
        });
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","hanin","h@n!nabbas123" + "" );
            Statement stmt=con.createStatement();
            String get_data = "select *from Client";
            ResultSet rs = stmt.executeQuery(get_data);
            while(rs.next()){
                cid = rs.getInt(1);
                cname = rs.getString(2);
                birthdate = rs.getString(3);
                gender = rs.getString(4);
                address = rs.getString(5);
                phonenum = rs.getString(6);
                table.getItems().add(new Client(cid,cname,birthdate,gender,address,phonenum));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void addClient(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml_files/add_client.fxml"));
        container.getChildren().setAll(pane);
    }

    @FXML
    private void editClientInfo(ActionEvent event){
        table.setEditable(true);
        name_col.setCellFactory(TextFieldTableCell.forTableColumn());
        date_col.setCellFactory(TextFieldTableCell.forTableColumn());
        gender_col.setCellFactory(TextFieldTableCell.forTableColumn());
        address_col.setCellFactory(TextFieldTableCell.forTableColumn());
        number_col.setCellFactory(TextFieldTableCell.forTableColumn());
        update.setVisible(true);
    }
    @FXML
    private void updateNameHandler(TableColumn.CellEditEvent event){
        Client client = table.getSelectionModel().getSelectedItem();
        String edit = "call updateNameClient("+client.getCid()+",'"+event.getNewValue()+"')";
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
        Client client = table.getSelectionModel().getSelectedItem();
        String edit = "call updateDateClient("+client.getCid()+",'"+event.getNewValue()+"')";
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
        Client client = table.getSelectionModel().getSelectedItem();
        String edit = "call updateGenderClient("+client.getCid()+",'"+event.getNewValue()+"')";
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
        Client client = table.getSelectionModel().getSelectedItem();
        String edit = "call updateAddressClient("+client.getCid()+",'"+event.getNewValue()+"')";
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
        Client client = table.getSelectionModel().getSelectedItem();
        String edit = "call updatePhoneClient("+client.getCid()+",'"+event.getNewValue()+"')";
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
    private void deleteClient(ActionEvent event){
        int cid = table.getSelectionModel().getSelectedItem().getCid();
        String delete_client = "call deleteCLient("+cid+")";
        table.getItems().remove(table.getSelectionModel().getSelectedItem());
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","hanin","h@n!nabbas123" + "" );
            Statement stmt=con.createStatement();
            stmt.executeQuery(delete_client);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void update(ActionEvent event){
        int cid;
        String cname,birthdate,gender,address,phonenum;
        table.getItems().removeAll(table.getItems());
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","hanin","h@n!nabbas123" + "" );
            Statement stmt=con.createStatement();
            String get_data = "select *from Client";
            ResultSet rs = stmt.executeQuery(get_data);
            while(rs.next()){
                cid = rs.getInt(1);
                cname = rs.getString(2);
                birthdate = rs.getString(3);
                gender = rs.getString(4);
                address = rs.getString(5);
                phonenum = rs.getString(6);
                table.getItems().add(new Client(cid,cname,birthdate,gender,address,phonenum));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void handleSearchEvent(ActionEvent event){
        String data = search_field.getText();
        String get_data = "select * from Client where Cid="+data;
    }
}
