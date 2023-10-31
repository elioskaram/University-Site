package project_m;

import java.sql.*;

public class dbConnection {
    public Connection getConnection() throws ClassNotFoundException, SQLException{
        
        Connection conn;
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic", "root", "elios@261018");
        System.out.println("Connection established successfully");
        return conn;
    }
}
