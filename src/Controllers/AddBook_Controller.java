package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AddBook_Controller implements Initializable {
    String server="localhost";
    int port=3306;
    String user="hanin";
    String password="h@n!nabbas123";
    String database="mydb";
    String jdbcurl;
    Connection con=null;
    @FXML
    Label no_name,no_amount,no_category,no_author;
    @FXML
    TextField name_field,amount_field,category_field,author_field;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private void handleSumbitEvenet(ActionEvent event){
        if(checkInfo())
            addBook();
    }
    private boolean checkInfo() {
        boolean flag=false;

        if(name_field.getText().isEmpty()){
            no_name.setText("Please enter your name");
            no_name.setTextFill(Color.web("red"));
            flag=false;
        }
        else
            no_name.setText("");
        if(amount_field.getText().isEmpty()){
            no_amount.setText("Please enter your email");
            no_amount.setTextFill(Color.web("red"));
            flag=false;
        }
        else
            no_amount.setText("");
        if(category_field.getText().isEmpty()){
            no_category.setText("Please enter your phone");
            no_category.setTextFill(Color.web("red"));
            flag=false;
        }
        else
            no_category.setText("");
        if(author_field.getText().isEmpty()){
            no_author.setText("Please enter date");
            no_author.setTextFill(Color.web("red"));
            flag=false;
        }
        else
            no_author.setText("");
        if(!name_field.getText().isEmpty()&&!author_field.getText().isEmpty()&&!category_field.getText().isEmpty()&&!amount_field.getText().isEmpty())
            flag = true;
        return flag;
    }
    private void addBook(){
        int amount=Integer.parseInt(amount_field.getText()),auth_id=-1,cat_id=-1;
        String book_name=name_field.getText();
        String get_auth_id="select Author_id from Author where AuthorName='"+author_field.getText()+"'";
        String get_cat_id="select Category_id from Category where CategoryName='"+category_field.getText()+"'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","hanin","h@n!nabbas123" + "" );
            Statement stmt=con.createStatement();
            ResultSet rs2 = stmt.executeQuery(get_auth_id);
            rs2.next();
            auth_id=rs2.getInt(1);
            ResultSet rs3 = stmt.executeQuery(get_cat_id);
            rs3.next();
            cat_id=rs3.getInt(1);
            String test ="call addBook('"+book_name+"'"+","+amount+","+auth_id+","+cat_id+")";
            ResultSet rs=stmt.executeQuery(test);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
