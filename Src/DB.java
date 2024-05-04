package Src;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.cj.jdbc.Driver;

@SuppressWarnings("unused")
/* Set connection to database 
 * Username is root
 * Pass word is 1 to 9
 */
public class DB {
    Connection c;
    java.sql.Statement s;
    DB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c= DriverManager.getConnection("jdbc:mysql:///cfrmanagementsystem","root","123456789");
            s=c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
