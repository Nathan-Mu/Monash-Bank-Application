/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entities.User;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author nathan
 */
@Remote
public interface UserRepository {
    
    public List<User> findAllUsers() throws Exception;
    
    public User searchUserById(int id) throws Exception;
    
    public void removeUser(int id) throws Exception;
    
    public void createUser(String fname, String lname, String type, String phoneNo, String email, String address, String password) throws Exception;
    
    public void editUser(User user) throws Exception;
    
    public boolean searchUserExistById(int id) throws Exception;
    
    public BigDecimal searchBalanceById(int id) throws Exception;
    
}
