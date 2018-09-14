package repository;

import entities.Transaction;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author nathan
 */
@Remote
public interface TransactionRepository {
    
    public List<Transaction> findAllTransactions() throws Exception;
    
    public Transaction searchTransactionById(int id) throws Exception;
    
    public List<Transaction> searchTransactionByType(String type) throws Exception;
    
    public List<Transaction> searchTransactionByName(String name) throws Exception;
    
    public List<Transaction> searchByCombination(String id, String name, String type) throws Exception;
    
    public void createTransaction(Transaction transaction) throws Exception;
    
    public int searchNumberOfTransByUserId(int id) throws Exception;
}
