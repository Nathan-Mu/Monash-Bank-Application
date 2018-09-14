package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author nathan
 */
@Entity
@Table(name = "BANK_TRANSACTION")
@NamedQueries({
    @NamedQuery(name = "Transaction.findAll", query = "select t from Transaction t"),
    @NamedQuery(name = "Transaction.findByType", query = "select t from Transaction t where t.type = :type"),
    @NamedQuery(name = "Transaction.findByTransactionName", query = "select t from Transaction t where t.transactionName = :transactionName")
})
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_NO")
    private int transactionNo;
    @Column(name = "TRANSACTION_NAME")
    private String transactionName;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "UID", referencedColumnName = "UID")
    @ManyToOne
    private User user;

    public Transaction() {
    }

    public Transaction(int transactionNo, String transactionName, String type, String description, User user, BigDecimal amount) {
        this.transactionNo = transactionNo;
        this.transactionName = transactionName;
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.user = user;
    }

    public int getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(int transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
