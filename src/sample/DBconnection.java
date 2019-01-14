package sample;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
public class DBconnection {
    String server = "localhost";
    int port = 3306;
    String user = "hanin";
    String password = "h@n!nabbas123";
    String database = "mydb";
    String jdbcurl;


    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "hanin", "h@n!nabbas123" + "");
            return con;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return  null;
        }

    }
}
