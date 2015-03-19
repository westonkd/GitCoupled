/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Application.User;
import java.util.List;
import java.sql.*;

/**
 *
 * @author McKay
 */
public class MySQLDao implements SoulDao {

    private String dbUrl = "jdbc:mysql://localhost:8889/gitcoupled";
    private String user = "root";
    private String password = "pass";
    private Connection conn = null;
    private Statement statement = null;

    public MySQLDao() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbUrl, user, password);

            statement = conn.createStatement();

        } catch (Exception ex) {
            System.out.println("=======================================");
            ex.printStackTrace();
            System.out.println(dbUrl);
            System.out.println(user);
            System.out.println(password);
            System.out.println("++++++++++++++++++++++++++++++++++++++++");
        }
    }

    @Override
    public void addUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUser(String username) {

        User user = null;

        try {
            String sql = "SELECT * FROM user WHERE github_username = '" + username + "'";
            ResultSet results = statement.executeQuery(sql);

            results.first();
            int id = results.getInt("id");
            String gender = results.getString("gender");
            int age = results.getInt("age");
            String github_username = results.getString("github_username");
            String quote = results.getString("quote");
            String bio = results.getString("bio");

            user = new User(id, gender, age, github_username, quote, bio);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getUsers(String primary, String secondary, String thirdly) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getUsers(String primary, String secondary) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getUsers(String primary) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
