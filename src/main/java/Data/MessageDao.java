/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Application.Message;
import Application.User;
import java.util.List;

/**
 *
 * @author McKay
 */
public interface MessageDao {
    
    public void open();
    public void close();
    
    public Message getMessage(int id);
    public List<Message> getMessages(String username);
    
    public void saveMessage(Message message);
    public void updateMessage(Message message);
    public int getNumMesssages(User user);
    
    public void deleteMessage(int id);
    
    public String printStatement(Message message);
}
