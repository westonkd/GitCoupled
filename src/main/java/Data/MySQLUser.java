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
public class MySQLUser implements SoulDao {

    private String dbUrl = "jdbc:mysql://localhost/gitcoupled";
    private String user = "Gandalf";
    private String password = "puremagic";
    private Connection conn = null;
    private Statement statement = null;
    private ResultSet results = null;

    public MySQLUser() {
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
                Logger.getLogger(MySQLUser.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(MySQLUser.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    @Override
    public List<User> getUsers(String primary, String secondary) {
        
        List<User> list = new ArrayList<User>();
        String sql;
        
        try {
            
            sql = "SELECT * FROM user WHERE first_language = \"" + primary + "\" AND second_language = \"" + secondary + "\"";
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
            Logger.getLogger(MySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    @Override
    public List<User> getUsers(String primary) {
        
        List<User> list = new ArrayList<User>();
        
        try {
            
            String sql = "SELECT * FROM user WHERE first_language = '" + primary + "'";
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
                String secondary = results.getString("second_language");
                String thirdly = results.getString("third_language");
                
                User user = new User(id, gender, age, username, quote, bio, score, primary, secondary, thirdly);
                list.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    @Override
    public void updateUser(User user) {
        
        String sql;
        
        if (user.getId() != -1)
            sql = "UPDATE user "
                    + "SET "
                    + "gender = '" + user.getGender() + "', "
                    + "age = " + user.getAge() + ", "
                    + "github_username = '" + user.getGithub_username() + "', "
                    + "quote = '" + user.getQuote() + "', "
                    + "bio = '" + user.getBio() + "', "
                    + "compat_score = " + user.getCompat_score()+ ", "
                    + "first_language = '" + user.getFirst_language() + "', "
                    + "second_language = '" + user.getSecond_language() + "', "
                    + "third_language = '" + user.getThird_language() + "' "
                    + "WHERE id = " + user.getId();
        else
            sql = "UPDATE user "
                    + "SET "
                    + "id = " + user.getId() + ", "
                    + "gender = '" + user.getGender() + "', "
                    + "age = " + user.getAge() + ", "
                    + "quote = '" + user.getQuote() + "', "
                    + "bio = '" + user.getBio() + "', "
                    + "compat_score = " + user.getCompat_score()+ ", "
                    + "first_language = '" + user.getFirst_language() + "', "
                    + "second_language = '" + user.getSecond_language() + "', "
                    + "third_language = '" + user.getThird_language() + "' "
                    + "WHERE github_username = '" + user.getGithub_username() + "'";
        
        try {
            
            statement.executeUpdate(sql);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param id
     * @param first
     * @param second
     * @param third
     */
    @Override
    public void saveLanguages(int id, String first, String second, String third) {
        
        if (id <= 0)
            throw new IllegalArgumentException("The id of the user has not been set");
        
        String sql = "UPDATE user "
                + "SET "
                + "first_language = '" + first + "', "
                + "second_language = '" + second + "', "
                + "third_language = '" + third + "' "
                + "WHERE id = " + id;
        
        try {
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     *
     * @param id
     * @param score
     */
    @Override
    public void saveScore(int id, int score) {
        
        if (id <= 0)
            throw new IllegalArgumentException("The id of the user has not been set");
        
        String sql = "UPDATE user "
                + "SET "
                + "compat_score = " + score + " "
                + "WHERE id = " + id;
        
        try {
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<User> usersThatHaveLanguage(String language) {
        
        List<User> users = new ArrayList();
        String sql = "SELECT * FROM user "
                + "WHERE "
                + "first_language = '" + language + "' OR "
                + "second_language = '" + language + "' OR "
                + "third_language = '" + language + "'";
        
        try {
            results = statement.executeQuery(sql);
            
            while(results.next())
            {
                int id = results.getInt("id");
                String gender = results.getString("gender");
                int age = results.getInt("age");
                String username = results.getString("github_username");
                String quote = results.getString("quote");
                String bio = results.getString("bio");
                int score = results.getInt("compat_score");
                String primary = results.getString("primary");
                String secondary = results.getString("second_language");
                String thirdly = results.getString("third_language");
                
                User user = new User(id, gender, age, username, quote, bio, score, primary, secondary, thirdly);
                users.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;
    }

}
