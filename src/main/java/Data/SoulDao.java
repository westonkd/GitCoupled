/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Application.User;
import java.util.List;

/**
 *
 * @author McKay
 */
public interface SoulDao {
    
    public void addUser(User user);
    public User getUser(String username);
    public User getUser(User user);

    public List<User> getUsers(String primary, String secondary, String thirdly);
    public List<User> getUsers(String primary, String secondary);
    public List<User> getUsers(String primary);
    public List<User> usersThatHaveLanguage(String language);
    
    public void updateUser(User user);
    public void saveLanguages(int id, String first, String second, String third);
    public void saveScore(int id, int score);
    
    
}
