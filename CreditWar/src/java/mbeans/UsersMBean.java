/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbeans;

import entities.User;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import repository.TransactionRepository;
import repository.UserRepository;

/**
 *
 * @author nathan
 */
@ManagedBean(name = "users")
@SessionScoped
public class UsersMBean {
    @EJB
    private UserRepository userRepository;
    @EJB
    private TransactionRepository transactionRepository;
    
    private List<User> users;
    private User user;
    private String uid;
    private String fname;
    private String lname;
    private String password;
    private String phoneNo;
    private String type;
    private String balance;
    private String email;
    private String address;
    private String confirmPassword;
    
    private boolean removable = true;
    private boolean samePassword = true;
    
    public UsersMBean() {
    }
    
    public List<User> getAllUsers()
    {
        try
        {
            return userRepository.findAllUsers();
        }
        catch (Exception e)
        {
            Logger.getLogger(UsersMBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return new ArrayList<User>();
    }
    

    
    public List<User> getUsers()
    {
        if (users == null) {
            users = getAllUsers();
        }
        return users;
    }
    
    public String removeUser(int id)
    {
        try
        {
            removable = getUserRemovableById(id);
            if (removable) {
                userRepository.removeUser(id); 
            }
        }
        catch (Exception e)
        {
            Logger.getLogger(UsersMBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return "userList";
    }
    
    public boolean getUserRemovableById(int id) {
        try
        {
            if (transactionRepository.searchNumberOfTransByUserId(id) == 0)
                return true;
            clean();
        }
        catch (Exception e)
        {
            Logger.getLogger(UsersMBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String createUser()
    {
        try
        {
            if (!password.equals(confirmPassword)) {
                samePassword = false;
            } else {
                userRepository.createUser(fname, lname, type, phoneNo, email, address, password);
                clean();
                return "userList";
            }
        }
        catch (Exception e)
        {
            Logger.getLogger(UsersMBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return "userCreate";
    }

    public boolean isRemovable() {
        return removable;
    }

    public void setRemovable(boolean removable) {
        this.removable = removable;
    }
    
    public void clean() {
        fname = "";
        lname = "";
        password = "";
        phoneNo = "";
        type = "";
        balance = "";
        email = "";
        address = "";
        confirmPassword = "";
        removable = true;
        samePassword = true;
    }
    
    public String back() {
        clean();
        return "index";
    }
    
    public String prepareCreate() {
        clean();
        return "userCreate";
    }
    
    public User getUser() {
        return user;
    }
    
    public String prepareView(int id) {
        user = findUserById(id);
        this.uid = String.valueOf(user.getUid());
        this.password = user.getPassword();
        this.address = user.getAddress();
        this.email = user.getEmail();
        this.fname = user.getFname();
        this.lname = user.getLname();
        this.phoneNo = user.getPhoneNo();
        this.type = user.getType();
        this.balance = String.valueOf(user.getBalance());
        return "userView";
    }
    
    public String prepareEdit(int id) {
        user = findUserById(id);
        this.uid = user.getUid() + "";
        this.password = user.getPassword();
        this.address = user.getAddress();
        this.email = user.getEmail();
        this.fname = user.getFname();
        this.lname = user.getLname();
        this.phoneNo = user.getPhoneNo();
        this.type = user.getType();
        this.balance = user.getBalance() + "";
        return "userEdit";
    }
    
    public User findUserById(int id) {
        try {
            return userRepository.searchUserById(id);
        } catch (Exception e) {
            Logger.getLogger(CreditMBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return new User();
    }
    
    public String editUser() {
        try {
            user.setAddress(address);
            user.setEmail(email);
            user.setFname(fname);
            user.setLname(lname);
            user.setPhoneNo(phoneNo);
            user.setType(type);
            userRepository.editUser(user);
            user = null;
            return "userList";
            
        } catch (Exception e) {
            Logger.getLogger(CreditMBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return "UserEdit";
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    
    public String backToList() {
        clean();
        return "userList";
    }
    
    public String prepareUserList() {
        clean();
        return "userList";
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isSamePassword() {
        return samePassword;
    }

    public void setSamePassword(boolean samePassword) {
        this.samePassword = samePassword;
    }
    
    
    
}
