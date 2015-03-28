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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author McKay
 */
public class MySQLUser implements SoulDao {

    private String dbUrl;
    private String user;
    private String password;
    private Connection conn;
    private Statement statement;
    private ResultSet results;

    public MySQLUser() {
        //dbUrl = "jdbc:mysql://localhost/gitcoupled";
        String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
        String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
        dbUrl = "jdbc:mysql://" + host + ":" + port + "/" + "gitcoupled";
        user = "Gandalf";
        password = "puremagic";
        conn = null;
        statement = null;
        results = null;
    }

    @Override
    public void open() {
        System.out.println(":D Opened a connection! <<<<<<<<<<<");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbUrl, user, password);

            statement = conn.createStatement();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            System.out.println("Cosed a connection! >>>>>>>>");
            results = null;
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addUser(User user) {
        open();

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

        close();
    }

    @Override
    public User getUser(String username) {
        open();
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

        close();
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
        open();
        if (user.getId() != -1) {
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
        } else if (user.getGithub_username() != null) {
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
        close();
        return user;
    }

    @Override
    public List<User> getUsers(String primary, String secondary, String thirdly) {
        open();
        List<User> list = new ArrayList<User>();

        try {

            String sql = "SELECT * FROM user WHERE "
                    + "first_language = '" + primary + "' AND "
                    + "second_language = '" + secondary + "' AND "
                    + "third_language = '" + thirdly + "'";
            results = statement.executeQuery(sql);

            while (results.next()) {
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

        close();
        return list;
    }

    @Override
    public List<User> getUsers(String primary, String secondary) {
        open();
        List<User> list = new ArrayList<User>();
        String sql;

        try {

            sql = "SELECT * FROM user WHERE first_language = \"" + primary + "\" AND second_language = \"" + secondary + "\"";
            results = statement.executeQuery(sql);

            while (results.next()) {
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

        close();
        return list;
    }

    @Override
    public List<User> getUsers(String primary) {
        close();
        List<User> list = new ArrayList<User>();

        try {

            String sql = "SELECT * FROM user WHERE first_language = '" + primary + "'";
            results = statement.executeQuery(sql);

            while (results.next()) {
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

        close();
        return list;
    }

    @Override
    public void updateUser(User user) {
        open();
        String sql;

        if (user.getId() != -1) {
            sql = "UPDATE user "
                    + "SET "
                    + "gender = '" + user.getGender() + "', "
                    + "age = " + user.getAge() + ", "
                    + "github_username = '" + user.getGithub_username() + "', "
                    + "quote = '" + user.getQuote() + "', "
                    + "bio = '" + user.getBio() + "', "
                    + "compat_score = " + user.getCompat_score() + ", "
                    + "first_language = '" + user.getFirst_language() + "', "
                    + "second_language = '" + user.getSecond_language() + "', "
                    + "third_language = '" + user.getThird_language() + "' "
                    + "WHERE id = " + user.getId();
        } else {
            sql = "UPDATE user "
                    + "SET "
                    + "id = " + user.getId() + ", "
                    + "gender = '" + user.getGender() + "', "
                    + "age = " + user.getAge() + ", "
                    + "quote = '" + user.getQuote() + "', "
                    + "bio = '" + user.getBio() + "', "
                    + "compat_score = " + user.getCompat_score() + ", "
                    + "first_language = '" + user.getFirst_language() + "', "
                    + "second_language = '" + user.getSecond_language() + "', "
                    + "third_language = '" + user.getThird_language() + "' "
                    + "WHERE github_username = '" + user.getGithub_username() + "'";
        }

        try {

            statement.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(MySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
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
        open();
        if (id <= 0) {
            throw new IllegalArgumentException("The id of the user has not been set");
        }

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
        close();
    }

    /**
     *
     * @param id
     * @param score
     */
    @Override
    public void saveScore(int id, int score) {
        open();
        if (id <= 0) {
            throw new IllegalArgumentException("The id of the user has not been set");
        }

        String sql = "UPDATE user "
                + "SET "
                + "compat_score = " + score + " "
                + "WHERE id = " + id;

        try {
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
    }

    @Override
    public List<User> usersThatHaveLanguage(String language) {
        open();
        List<User> users = new ArrayList();
        String sql = "SELECT * FROM user "
                + "WHERE "
                + "first_language = '" + language + "' OR "
                + "second_language = '" + language + "' OR "
                + "third_language = '" + language + "'";

        try {
            results = statement.executeQuery(sql);

            while (results.next()) {
                int id = results.getInt("id");
                String gender = results.getString("gender");
                int age = results.getInt("age");
                String username = results.getString("github_username");
                String quote = results.getString("quote");
                String bio = results.getString("bio");
                int score = results.getInt("compat_score");
                String primary = results.getString("first_language");
                String secondary = results.getString("second_language");
                String thirdly = results.getString("third_language");

                User user = new User(id, gender, age, username, quote, bio, score, primary, secondary, thirdly);
                users.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        close();
        return users;
    }

    @Override
    public List<User> usersThatHaveLanguages(String aLanguage, String bLanguage) {
        open();
        List<User> users = new ArrayList();
        String sql = "SELECT * FROM user "
                + "WHERE "
                + "(first_language = '" + aLanguage + "' OR "
                + "second_language = '" + aLanguage + "' OR "
                + "third_language = '" + aLanguage + "') "
                + "AND "
                + "(first_language = '" + bLanguage + "' OR  "
                + "second_language = '" + bLanguage + "' OR  "
                + "third_language = '" + bLanguage + "') ";

        try {
            results = statement.executeQuery(sql);

            while (results.next()) {
                int id = results.getInt("id");
                String gender = results.getString("gender");
                int age = results.getInt("age");
                String username = results.getString("github_username");
                String quote = results.getString("quote");
                String bio = results.getString("bio");
                int score = results.getInt("compat_score");
                String primary = results.getString("first_language");
                String secondary = results.getString("second_language");
                String thirdly = results.getString("third_language");

                User user = new User(id, gender, age, username, quote, bio, score, primary, secondary, thirdly);
                users.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        close();
        return users;
    }

    @Override
    public List<User> usersThatHaveLanguages(String aLanguage, String bLanguage, String cLanguage) {
        open();
        List<User> users = new ArrayList();
        String sql = "SELECT * FROM user "
                + "WHERE "
                + "(first_language = '" + aLanguage + "' OR "
                + "second_language = '" + aLanguage + "' OR "
                + "third_language = '" + aLanguage + "') "
                + "AND "
                + "(first_language = '" + bLanguage + "' OR  "
                + "second_language = '" + bLanguage + "' OR  "
                + "third_language = '" + bLanguage + "') "
                + "AND "
                + "(first_language = '" + cLanguage + "' OR "
                + "second_language = '" + cLanguage + "' OR "
                + "third_language = '" + cLanguage + "')";

        try {
            results = statement.executeQuery(sql);

            while (results.next()) {
                int id = results.getInt("id");
                String gender = results.getString("gender");
                int age = results.getInt("age");
                String username = results.getString("github_username");
                String quote = results.getString("quote");
                String bio = results.getString("bio");
                int score = results.getInt("compat_score");
                String primary = results.getString("first_language");
                String secondary = results.getString("second_language");
                String thirdly = results.getString("third_language");

                User user = new User(id, gender, age, username, quote, bio, score, primary, secondary, thirdly);
                users.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        close();
        return users;
    }

    @Override
    public int getUserId(String username) {
        open();
        int id = -1;
        String sql = "Select id From user "
                + "WHERE github_username = '" + username + "'";

        try {

            results = statement.executeQuery(sql);
            results.first();

            id = results.getInt("id");
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        close();
        return id;
    }

    @Override
    public Map<Integer, Set<User>> getMatchesWithScores(User user) {
        open();
        Map<Integer, Set<User>> matches = new TreeMap<Integer, Set<User>>().descendingMap();
        String first = user.getFirst_language();
        String second = user.getSecond_language();
        String third = user.getThird_language();

        String sql = "SELECT * FROM ( "
                + "(SELECT *, 10 as 'score' FROM user "
                + "WHERE "
                + "first_language  = '" + first + "' AND "
                + "second_language = '" + second + "' AND "
                + "third_language  = '" + third + "') "
                + "UNION "
                + "(SELECT *, 9 as 'score' FROM user "
                + "WHERE "
                + "first_language  = '" + first + "' AND "
                + "second_language = '" + second + "') "
                + "UNION "
                + "(SELECT *, 8 as 'score' FROM user "
                + "WHERE  "
                + "first_language  = '" + first + "' AND "
                + "third_language  = '" + third + "') "
                + "UNION "
                + "(SELECT *, 7 as 'score' FROM user "
                + "WHERE  "
                + "second_language  = '" + second + "' AND "
                + "third_language = '" + third + "') "
                + "UNION "
                + "(SELECT *, 6 as 'score' FROM user "
                + "WHERE  "
                + "(first_language  = '" + first + "' OR  "
                + "second_language = '" + first + "' OR  "
                + "third_language  = '" + first + "')  "
                + "AND "
                + "(first_language  = '" + second + "' OR "
                + "second_language = '" + second + "' OR "
                + "third_language  = '" + second + "')  "
                + "AND  "
                + "(first_language  = '" + third + "' OR  "
                + "second_language = '" + third + "' OR "
                + "third_language  = '" + third + "')) "
                + "UNION "
                + "(SELECT *, 5 as 'score' FROM user "
                + "WHERE  "
                + "(first_language  = '" + first + "' OR  "
                + "second_language = '" + first + "' OR  "
                + "third_language  = '" + first + "')  "
                + "AND "
                + "(first_language  = '" + second + "' OR "
                + "second_language = '" + second + "' OR "
                + "third_language  = '" + second + "')) "
                + "UNION "
                + "(SELECT *, 5 as 'score' FROM user "
                + "WHERE  "
                + "(first_language  = '" + first + "' OR  "
                + "second_language = '" + first + "' OR  "
                + "third_language  = '" + first + "')  "
                + "AND  "
                + "(first_language  = '" + third + "' OR  "
                + "second_language = '" + third + "' OR "
                + "third_language  = '" + third + "')) "
                + "UNION "
                + "(SELECT *, 5 as 'score' FROM user "
                + "WHERE  "
                + "(first_language  = '" + second + "' OR "
                + "second_language = '" + second + "' OR "
                + "third_language  = '" + second + "')  "
                + "AND  "
                + "(first_language  = '" + third + "' OR  "
                + "second_language = '" + third + "' OR "
                + "third_language  = '" + third + "')) "
                + "UNION "
                + "(SELECT *, 4 as 'score' FROM user "
                + "WHERE "
                + " first_language  = '" + first + "') "
                + "UNION "
                + "(SELECT *, 3 as 'score' FROM user "
                + "WHERE "
                + "second_language = '" + second + "') "
                + "UNION "
                + "(SELECT *, 2 as 'score' FROM user "
                + "WHERE "
                + "third_language  = '" + third + "') "
                + "UNION "
                + "(SELECT *, 1 as 'score' FROM user "
                + "WHERE "
                + "(first_language IN('" + first + "', '" + second + "', '" + third + "') OR "
                + "second_language IN('" + first + "', '" + second + "', '" + third + "') OR "
                + "third_language IN('" + first + "', '" + second + "', '" + third + "'))) "
                + ") temp "
                + "WHERE github_username != '" + user.getGithub_username() + "' "
                + "GROUP BY id, github_username ";

        try {
            results = statement.executeQuery(sql);

            while (results.next()) {
                int id = results.getInt("id");
                String gender = results.getString("gender");
                int age = results.getInt("age");
                String username = results.getString("github_username");
                String quote = results.getString("quote");
                String bio = results.getString("bio");
                int compat_score = results.getInt("compat_score");
                String primary = results.getString("first_language");
                String secondary = results.getString("second_language");
                String thirdly = results.getString("third_language");
                int score = results.getInt("score");

                User match = new User(id, gender, age, username, quote, bio, compat_score, primary, secondary, thirdly);

                if (matches.containsKey(score)) {
                    matches.get(score).add(match);
                } else {
                    //create the new set with the user
                    Set<User> temp = new HashSet<>();
                    temp.add(match);

                    matches.put(score, temp);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(MySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        close();
        return matches;
    }

    @Override
    public Set<User> getMatches(User user) {
        open();
        Set<User> matches = new HashSet();
        String first = user.getFirst_language();
        String second = user.getSecond_language();
        String third = user.getThird_language();

        String sql = "SELECT * FROM ( "
                + "(SELECT * FROM user "
                + "WHERE "
                + "first_language  = '" + first + "' AND "
                + "second_language = '" + second + "' AND "
                + "third_language  = '" + third + "') "
                + "UNION "
                + "(SELECT * FROM user "
                + "WHERE "
                + "first_language  = '" + first + "' AND "
                + "second_language = '" + second + "') "
                + "UNION "
                + "(SELECT * FROM user "
                + "WHERE  "
                + "first_language  = '" + first + "' AND "
                + "third_language  = '" + third + "') "
                + "UNION "
                + "(SELECT * FROM user "
                + "WHERE  "
                + "second_language  = '" + second + "' AND "
                + "third_language = '" + third + "') "
                + "UNION "
                + "(SELECT * FROM user "
                + "WHERE  "
                + "(first_language  = '" + first + "' OR  "
                + "second_language = '" + first + "' OR  "
                + "third_language  = '" + first + "')  "
                + "AND "
                + "(first_language  = '" + second + "' OR "
                + "second_language = '" + second + "' OR "
                + "third_language  = '" + second + "')  "
                + "AND  "
                + "(first_language  = '" + third + "' OR  "
                + "second_language = '" + third + "' OR "
                + "third_language  = '" + third + "')) "
                + "UNION "
                + "(SELECT * FROM user "
                + "WHERE  "
                + "(first_language  = '" + first + "' OR  "
                + "second_language = '" + first + "' OR  "
                + "third_language  = '" + first + "')  "
                + "AND "
                + "(first_language  = '" + second + "' OR "
                + "second_language = '" + second + "' OR "
                + "third_language  = '" + second + "')) "
                + "UNION "
                + "(SELECT * FROM user "
                + "WHERE  "
                + "(first_language  = '" + first + "' OR  "
                + "second_language = '" + first + "' OR  "
                + "third_language  = '" + first + "')  "
                + "AND  "
                + "(first_language  = '" + third + "' OR  "
                + "second_language = '" + third + "' OR "
                + "third_language  = '" + third + "')) "
                + "UNION "
                + "(SELECT * FROM user "
                + "WHERE  "
                + "(first_language  = '" + second + "' OR "
                + "second_language = '" + second + "' OR "
                + "third_language  = '" + second + "')  "
                + "AND  "
                + "(first_language  = '" + third + "' OR  "
                + "second_language = '" + third + "' OR "
                + "third_language  = '" + third + "')) "
                + "UNION "
                + "(SELECT * FROM user "
                + "WHERE "
                + " first_language  = '" + first + "') "
                + "UNION "
                + "(SELECT * FROM user "
                + "WHERE "
                + "second_language = '" + second + "') "
                + "UNION "
                + "(SELECT * FROM user "
                + "WHERE "
                + "third_language  = '" + third + "') "
                + "UNION "
                + "(SELECT * FROM user "
                + "WHERE "
                + "(first_language IN('" + first + "', '" + second + "', '" + third + "') OR "
                + "second_language IN('" + first + "', '" + second + "', '" + third + "') OR "
                + "third_language IN('" + first + "', '" + second + "', '" + third + "'))) "
                + ") temp "
                + "WHERE github_username != '" + user.getGithub_username() + "' "
                + "GROUP BY id, github_username ";

        try {
            results = statement.executeQuery(sql);

            while (results.next()) {
                int id = results.getInt("id");
                String gender = results.getString("gender");
                int age = results.getInt("age");
                String username = results.getString("github_username");
                String quote = results.getString("quote");
                String bio = results.getString("bio");
                int compat_score = results.getInt("compat_score");
                String primary = results.getString("first_language");
                String secondary = results.getString("second_language");
                String thirdly = results.getString("third_language");

                User match = new User(id, gender, age, username, quote, bio, compat_score, primary, secondary, thirdly);

                matches.add(match);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        close();
        return matches;

    }

}
