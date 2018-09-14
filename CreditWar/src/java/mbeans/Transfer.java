/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbeans;

import entities.Transaction;
import entities.User;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import repository.TransactionRepository;
import repository.UserRepository;

/**
 *
 * @author nathan
 */
@ManagedBean(name = "transfer")
@SessionScoped
public class Transfer {
    @EJB
    private TransactionRepository transactionRepository;
    @EJB
    private UserRepository userRepository;
    
    private String transferFrom;
    private String transferTo;
    private String amount;
    private String description;
    private String transactionName;
    private String type;
    
    private boolean validTransferTo = true;
    private boolean validTransferFrom = true;
    private boolean differentUserId = true;
    private boolean enoughFund = true;
    
    public Transfer()
    {
        
    }
    
    public String transfer()
    {
        try
        {
            if (!userRepository.searchUserExistById(Integer.valueOf(transferFrom))) {
                validTransferFrom = false;
                return "transfer";
            } else if (!userRepository.searchUserExistById(Integer.valueOf(transferTo))) {
                validTransferTo = false;
                return "transfer";
            } else if (transferFrom.equalsIgnoreCase(transferTo)) {
                differentUserId = false;
                return "transfer";
            } else if (userRepository.searchBalanceById(Integer.valueOf(transferFrom)).compareTo(BigDecimal.valueOf(Double.valueOf(amount))) < 0) {
                enoughFund = false;
                return "transfer";
            } else {
                User user1 = userRepository.searchUserById(Integer.valueOf(transferFrom));
                User user2 = userRepository.searchUserById(Integer.valueOf(transferTo));
                Transaction transaction1 = new Transaction(0, transactionName, "out", description, user1, BigDecimal.valueOf(Double.valueOf(amount)));
                Transaction transaction2 = new Transaction(0, transactionName, "in", description, user2, BigDecimal.valueOf(Double.valueOf(amount)));
                transactionRepository.createTransaction(transaction1);
                transactionRepository.createTransaction(transaction2);
                user1.setBalance(user1.getBalance().subtract(BigDecimal.valueOf(Double.valueOf(amount))));
                user2.setBalance(user2.getBalance().add(BigDecimal.valueOf(Double.valueOf(amount))));
                userRepository.editUser(user1);
                userRepository.editUser(user2);
                return "transferSuccess";
            }
        }
        catch (Exception e)
        {
            Logger.getLogger(Transfer.class.getName()).log(Level.SEVERE, null, e);
        }
        return "transfer";
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getTransferFrom() {
        return transferFrom;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public String getTransferTo() {
        return transferTo;
    }

    public void setTransferTo(String transferTo) {
        this.transferTo = transferTo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transaction_name) {
        this.transactionName = transaction_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isValidTransferTo() {
        return validTransferTo;
    }

    public void setValidTransferTo(boolean validTransferTo) {
        this.validTransferTo = validTransferTo;
    }

    public boolean isValidTransferFrom() {
        return validTransferFrom;
    }

    public void setValidTransferFrom(boolean validTransferFrom) {
        this.validTransferFrom = validTransferFrom;
    }

    public boolean isDifferentUserId() {
        return differentUserId;
    }

    public void setDifferentUserId(boolean differentUserId) {
        this.differentUserId = differentUserId;
    }

    public boolean isEnoughFund() {
        return enoughFund;
    }

    public void setEnoughFund(boolean enoughFund) {
        this.enoughFund = enoughFund;
    }

    public void clean() {
        transferFrom = "";
        transferTo = "";
        amount = "";
        description = "";
        transactionName = "";
        type = "";
        validTransferTo = true;
        validTransferFrom = true;
        differentUserId = true;
        enoughFund = true;
    }
    
    public String prepareTransfer() {
        clean();
        return "transfer";
    }
    
}
