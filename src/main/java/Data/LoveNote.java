/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Application.Message;
import java.sql.*;
import java.util.List;

/**
 *
 * @author McKay
 */
public class LoveNote implements LovenoteDao {
    
    private String dbUrl = "jdbc:mysql://localhost/gitcoupled";
    private String user = "Gandalf";
    private String password = "puremagic";
    private Connection conn = null;
    private Statement statement = null;
    private ResultSet results = null;

    public LoveNote() {
    }

    @Override
    public List<Message> getMessages(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Message> getMessages(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveMessage(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteMessage(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
