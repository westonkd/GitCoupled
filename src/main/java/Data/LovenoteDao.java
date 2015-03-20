/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Application.Message;
import java.util.List;

/**
 *
 * @author McKay
 */
public interface LovenoteDao {
    
    public List<Message> getMessages(int id);
    public List<Message> getMessages(String username);
    
    public void saveMessage(Message message);
    
    public void deleteMessage(int id);
}
