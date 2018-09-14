package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author nathan
 */
@Entity
@Table(name = "BANK_USER")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "select u from User u")
})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UID")
    private int uid;
    @Column(name = "FNAME")
    private String fname;
    @Column(name = "LNAME")
    private String lname;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "PHONE_NO")
    private String phoneNo;
    @Column(name = "BALANCE")
    private BigDecimal balance;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Transaction> transactions;

    public User() {
    }

    public User(int uid, String fname, String lname, String email, String password, String type, String address, String phoneNo, BigDecimal balance) {
        this.uid = uid;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.type = type;
        this.address = address;
        this.phoneNo = phoneNo;
        this.balance = balance;
        this.transactions = new HashSet<>();
    }
    
    public User(String fname, String lname, String email, String password, String type, String address, String phoneNo) {
        this.uid = 0;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.type = type;
        this.address = address;
        this.phoneNo = phoneNo;
        this.balance = BigDecimal.valueOf(0);
        this.transactions = new HashSet<>();
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    
}

