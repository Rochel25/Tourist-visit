package sample.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connect {
    public Connection connection;
    public Connection getConnection(){
        String dbname= "visite";
        String username= "root";
        String pwd="";

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost/"+dbname,username,pwd);
        }
        catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
