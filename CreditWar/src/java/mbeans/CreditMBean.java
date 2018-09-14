package mbeans;

import entities.Transaction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import repository.TransactionRepository;

/**
 *
 * @author nathan
 */
@ManagedBean(name = "credit")
@SessionScoped
public class CreditMBean implements Serializable{
    @EJB
    private TransactionRepository transactionRepository;
    
    private List<Transaction> transactions;
    private String transactionNo = "";
    private String transactionName = "";
    private String transactionType = "";
    
    public CreditMBean() {
    }
    
    public List<Transaction> getAllTransactions()
    {
        try
        {
            return transactionRepository.findAllTransactions();
        }
        catch (Exception e)
        {
            Logger.getLogger(CreditMBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return new ArrayList<Transaction>();
    }
    

    
    public List<Transaction> getTransactions()
    {
        if (transactions == null) {
            transactions = getAllTransactions();
        }
        return transactions;
    }
    
    public String search() {
        try {
            transactions = transactionRepository.searchByCombination(transactionNo, transactionName, transactionType);
        } catch (Exception e) {
            Logger.getLogger(CreditMBean.class.getName()).log(Level.SEVERE, null, e);
        }
            return "transactionList";
    }

    public TransactionRepository getTransactionRepository() {
        return transactionRepository;
    }

    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    
    public void clean() {
        transactions = null;
        transactionNo = "";
        transactionName = "";
        transactionType = "";
    }
    
    public String prepareTransactionList() {
        clean();
        return "transactionList";
    }
}

