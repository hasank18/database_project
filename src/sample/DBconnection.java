package sample;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBconnection {
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
