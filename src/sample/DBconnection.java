package sample;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
public class DBconnection {
    public static void main(String[] args) {
        String server="localhost";
        int port=3306;
        String user="hanin";
        String password="h@n!nabbas123";
        String database="mydb";
        String jdbcurl;
        Connection con=null;


        try {
            System.out.println("hi");
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","hanin","h@n!nabbas123" + "" );
            System.out.println("hellooo");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from Author");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  ");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}