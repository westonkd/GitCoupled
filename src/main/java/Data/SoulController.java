/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.*;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author McKay
 */
public class SoulController {
    
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   private static final String database_url = "jdbc:mysql://127.3.91.130/gitcoupled";

   //  Database credentials
   private static final String username = "Gandalf";
   private static final String password = "puremagic";
    
    public static Connection connect()
    {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn =  DriverManager.getConnection(database_url, username, password);
}
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        return conn;
        
    }
    
    public static boolean insert(String table, String column, String value)
    {
        Connection conn = connect();
        
        if (conn != null)
        {
            
        }
        
        return false;
    }
    
    public static Map<String, String> select(String table, String [] columns)
    {
        Map<String, String> map = null;
        Connection conn = connect();
        String sql = "SELECT ";
        
        try {
            Statement stmt = conn.createStatement();
           
           for (String column : columns)
           {
               sql += column + ' ';
           }
           
           sql += "FROM " + table;
           ResultSet rs = stmt.executeQuery(sql);
           
           while(rs.next())
           {
               for (int i = 0; i < columns.length; i++)
               {
                   map.put(columns[i], rs.getString(columns[i]));
               }
           }
           
       } catch (SQLException ex) {
           Logger.getLogger(SoulController.class.getName()).log(Level.SEVERE, null, ex);
       }
               
       return map;
    }
    
    
}
