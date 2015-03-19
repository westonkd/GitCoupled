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

    private String dbUrl = "jdbc:mysql://localhost/gitcoupled";
    private String user = "Gandalf";
    private String password = "puremagic";
    private Connection conn = null;
    private Statement statement = null;

    public MySQLDao() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbUrl, user, password);

            statement = conn.createStatement();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) {
        try {
            String sql = "INSERT INTO User (gender,age,github_username,quote,bio,compat_score,first_language,second_language,third_language) VALUES ('" + user.getGender() + "'," + user.getAge() + ",'" + user.getGithub_username() + "',\"" + user.getQuote() + "\",\"" + user.getBio() + "\"," + user.getCompat_score() + ",'" + user.getFirst_language() + "','" + user.getSecond_language() + "','" + user.getThird_language() + "')";
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&" + sql);
            statement.executeUpdate(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
