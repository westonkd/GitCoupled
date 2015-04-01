/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Application.Message;
import Application.User;
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
            int in_reply_to = results.getInt("in_reply_to");
            int display = results.getInt("display");
            String from = results.getString("from");
            String to = results.getString("to");            
        
            lovenote = new Message(id, subject, body, sent_date, sent_from, recieved_by, in_reply_to, display, from, to);
            
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
                        "WHERE rb.github_username = '" + username + "' AND " +
                        "display = " + 1 + " " +
                        "ORDER BY sent_date DESC";
        
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
                int in_reply_to = results.getInt("in_reply_to");
                int display = results.getInt("display");
                String from = results.getString("from");            
        
                Message lovenote = new Message(id, subject, body, sent_date, sent_from, recieved_by, in_reply_to, display, from, username);
                
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
        
        String reply;
        if (message.getIn_reply_to() < 0)
            reply = " NULL";
        else
            reply = " " + message.getIn_reply_to();
        
        open();
        String sql = "INSERT INTO message "
                + "(subject, body, sent_date, sent_from, recieved_by, in_reply_to, display) "
                + "VALUES "
                + "( '" + message.getSubject() + "'"
                + ", '" + message.getBody() + "'"
                + ", NOW() "
                + ", " + message.getSent_from()
                + ", " + message.getRecieved_by()
                + "," + reply
                + ", " + 1
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
    public void updateMessage(Message message) {
        
        String replyTo;
        if (message.getIn_reply_to() <= 0)
            replyTo = " NULL";
        else 
            replyTo = " " + message.getIn_reply_to();
        
        open();
        
        String sql = "UPDATE message "
                + "SET "
                + "subject = '" + message.getSubject() + "', "
                + "body = '" + message.getBody() + "', "
                + "sent_date = '" + message.getSent_date() + "', "
                + "sent_from = " + message.getSent_from() + ", "
                + "recieved_by = " + message.getRecieved_by() + ", "
                + "in_reply_to =" + replyTo + ", "
                + "display = " + message.getDisplay() + " "
                + "WHERE "
                + "id = " + message.getId();
        
        try {
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
                        
        close();
    }

    @Override
    public int getNumMesssages(User user) {
        
        open();
        int count = 0;
        String sql = "SELECT count(*) as 'count' "
                + "FROM message "
                + "WHERE "
                + "recieved_by = " + user.getId() + " AND "
                + "display = 1 ";
        
        try {
            results = statement.executeQuery(sql);
            results.first();
            count = results.getInt("count");
        } catch (SQLException ex) {
            Logger.getLogger(MySQLMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        close();
        return count;
    }
    
    @Override
    public String printStatement(Message message) {
        
        return "UPDATE message "
                + "SET "
                + "subject = '" + message.getSubject() + "', "
                + "body = '" + message.getBody() + "', "
                + "sent_date = '" + message.getSent_date() + "', "
                + "sent_from = " + message.getSent_from() + ", "
                + "recieved_by = " + message.getRecieved_by() + ", "
                + "in_reply_to = " + message.getIn_reply_to() + ", "
                + "display = " + message.getDisplay() + " "
                + "WHERE "
                + "id = " + message.getId();
        
    }
    
}
