/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Application.Message;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author McKay
 */
public class MySQLMessage implements MessageDao {
    
    private String dbUrl;
    private String user;
    private String password;
    private Connection conn;
    private Statement statement;
    private ResultSet results;

    public MySQLMessage() {
        
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
    public void open()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbUrl, user, password);

            statement = conn.createStatement();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void close()
    {
        try {
            results = null;
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Message getMessage(int id) {
        
        open();
        Message lovenote = null;
        
        try {
            String sql = "SELECT m.*, sf.github_username as 'from', rb.github_username as 'to' " +
                        "FROM message m " +
                        "JOIN (SELECT id, github_username from user) sf " +
                        "ON m.sent_from = sf.id " +
                        "JOIN (SELECT id, github_username from user) rb " +
                        "ON m.recieved_by = rb.id " +
                        "WHERE m.id = " + id;
            results = statement.executeQuery(sql);
            
            results.first();
            String subject = results.getString("subject");
            String body = results.getString("body");
            Timestamp sent_date = results.getTimestamp("sent_date");
            int sent_from = results.getInt("sent_from");
            int recieved_by = results.getInt("recieved_by");
            String from = results.getString("from");
            String to = results.getString("to");            
        
            lovenote = new Message(id, subject, body, sent_date, sent_from, recieved_by, from, to);
            
        } catch (SQLException ex) {
            Logger.getLogger(MySQLMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        close();
        return lovenote;
        
    }

    @Override
    public List<Message> getMessages(String username) {
        
        open();
        List<Message> lovenotes = new ArrayList();
        
        String sql = "SELECT m.*, sf.github_username as 'from', rb.github_username as 'to' " +
                        "FROM message m " +
                        "JOIN (SELECT id, github_username from user) sf " +
                        "ON m.sent_from = sf.id " +
                        "JOIN (SELECT id, github_username from user) rb " +
                        "ON m.recieved_by = rb.id " +
                        "WHERE rb.github_username = '" + username +"' " +
                        "ORDER BY id DESC";
        
        try {
            
            results = statement.executeQuery(sql);
            
            while (results.next())
            {
                int id = results.getInt("id");
                String subject = results.getString("subject");
                String body = results.getString("body");
                Timestamp sent_date = results.getTimestamp("sent_date");
                int sent_from = results.getInt("sent_from");
                int recieved_by = results.getInt("recieved_by");
                String from = results.getString("from");            
        
                Message lovenote = new Message(id, subject, body, sent_date, sent_from, recieved_by, from, username);
                
                lovenotes.add(lovenote);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MySQLMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        close();
        return lovenotes;
        
    }

    @Override
    public void saveMessage(Message message) {
        
        open();
        String sql = "INSERT INTO message "
                + "(subject, body, sent_date, sent_from, recieved_by) "
                + "VALUES "
                + "( '" + message.getSubject() + "'"
                + ", '" + message.getBody() + "'"
                + ", NOW() "
                + ", " + message.getSent_from()
                + ", " + message.getRecieved_by()
                + ")";
        
        try {
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        close();
    }

    @Override
    public void deleteMessage(int id) {
        
        open();
        String sql = "DELETE FROM message WHERE id = " + id;
        
        try {
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        close();
    }

    @Override
    public String printStatement(Message message) {
        
        return "INSERT INTO message "
                + "(subject, body, sent_date, sent_from, recieved_by) "
                + "VALUES "
                + "( '" + message.getSubject() + "'"
                + ", '" + message.getBody() + "'"
                + ", NOW() "
                + ", " + message.getSent_from()
                + ", " + message.getRecieved_by()
                + ")";
        
    }
    
}
