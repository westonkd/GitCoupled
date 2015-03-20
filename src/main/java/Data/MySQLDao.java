/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Application.User;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author McKay
 */
public class MySQLDao implements SoulDao {

    private String dbUrl = "jdbc:mysql://localhost:8889/gitcoupled";
    //private String dbUrl = "jdbc:mysql://localhost/gitcoupled";
//    private String user = "root";
//    private String password = "pass";
    private String user = "Gandalf";
    private String password = "puremagic";
    private Connection conn = null;
    private Statement statement = null;
    private ResultSet results = null;

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
            String sql = "INSERT INTO User (gender,age,github_username,quote,bio,compat_score,first_language,second_language,third_language) "
                    + "VALUES "
                    + "('" + user.getGender() 
                    + "'," + user.getAge() 
                    + ",'" + user.getGithub_username() 
                    + "',\"" + user.getQuote() 
                    + "\",\"" + user.getBio() 
                    + "\"," + user.getCompat_score() 
                    + ",'" + user.getFirst_language() 
                    + "','" + user.getSecond_language() 
                    + "','" + user.getThird_language() + "')";
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
            results = statement.executeQuery(sql);

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
    
    /**
     * The user must have an ID or a github_username to be found.
     * 
     * @param user
     * @return 
     */
    @Override
    public User getUser(User user) {
        
        if (user.getId() != -1)
        {
            try {
                String sql = "SELECT * FROM user WHERE id = " + user.getId();
                results = statement.executeQuery(sql);
                results.first();
                
                user.setGender(results.getString("gender"));
                user.setAge(results.getInt("age"));
                user.setGithub_username(results.getString("github_username"));
                user.setQuote(results.getString("quote"));
                user.setBio(results.getString("bio"));
                user.setCompat_score(results.getInt("compat_score"));
                user.setFirst_language(results.getString("first_language"));
                user.setSecond_language(results.getString("second_language"));
                user.setThird_language(results.getString("third_language"));
                
            } catch (SQLException ex) {
                Logger.getLogger(MySQLDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (user.getGithub_username() != null)
        {
            try {
                String sql = "SELECT * FROM user WHERE github_username = '" + user.getGithub_username() + "'";
                results = statement.executeQuery(sql);
                results.first();
                
                user.setId(results.getInt("id"));
                user.setGender(results.getString("gender"));
                user.setAge(results.getInt("age"));
                user.setQuote(results.getString("quote"));
                user.setBio(results.getString("bio"));
                user.setCompat_score(results.getInt("compat_score"));
                user.setFirst_language(results.getString("first_language"));
                user.setSecond_language(results.getString("second_language"));
                user.setThird_language(results.getString("third_language"));
            } catch (SQLException ex) {
                Logger.getLogger(MySQLDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return user;
    }

    @Override
    public List<User> getUsers(String primary, String secondary, String thirdly) {
        
        List<User> list = new ArrayList<User>();
        
        try {
            
            String sql = "SELECT * FROM user WHERE "
                    + "first_language = '" + primary + "' AND "
                    + "second_language = '" + secondary + "' AND "
                    + "third_language = '" + thirdly + "'";
            results = statement.executeQuery(sql);
            
            while (results.next())            
            {
                int id = results.getInt("id");
                String gender = results.getString("gender");
                int age = results.getInt("age");
                String username = results.getString("github_username");
                String quote = results.getString("quote");
                String bio = results.getString("bio");
                int score = results.getInt("compat_score");
                
                User user = new User(id, gender, age, username, quote, bio, score, primary, secondary, thirdly);
                list.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    @Override
    public List<User> getUsers(String primary, String secondary) {
        
        List<User> list = new ArrayList<User>();
        
        try {
            
            String sql = "SELECT * FROM user WHERE "
                    + "first_language = '" + primary + "' AND "
                    + "second_language = '" + secondary + "'";
            results = statement.executeQuery(sql);
            
            while (results.next())            
            {
                int id = results.getInt("id");
                String gender = results.getString("gender");
                int age = results.getInt("age");
                String username = results.getString("github_username");
                String quote = results.getString("quote");
                String bio = results.getString("bio");
                int score = results.getInt("compat_score");
                String thirdly = results.getString("third_language");
                
                User user = new User(id, gender, age, username, quote, bio, score, primary, secondary, thirdly);
                list.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    @Override
    public List<User> getUsers(String primary) {
        
        List<User> list = new ArrayList<User>();
        
        try {
            
            String sql = "SELECT * FROM user WHERE "
                    + "first_language = '" + primary + "''";
            results = statement.executeQuery(sql);
            
            while (results.next())            
            {
                User user = new User(results);
                list.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

}
