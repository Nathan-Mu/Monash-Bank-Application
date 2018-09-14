package repository;

import entities.Transaction;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author nathan
 */
@Stateless
public class TransactionRepositoryImpl implements TransactionRepository {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Transaction> findAllTransactions() throws Exception {
        Query query = em.createNamedQuery("Transaction.findAll");
        return query.getResultList();
    }

    @Override
    public Transaction searchTransactionById(int id) throws Exception {
        Transaction transaction = em.find(Transaction.class, id);
        return transaction;
    }

    @Override
    public List<Transaction> searchTransactionByType(String type) throws Exception {
        Query query = em.createNamedQuery("Transaction.findByType");
        query.setParameter("type", type);
        return query.getResultList();
    }

    @Override
    public List<Transaction> searchTransactionByName(String name) throws Exception {
        Query query = em.createNamedQuery("Transaction.findByTransactionName");
        query.setParameter("transactionName", name);
        return query.getResultList();
    }

    @Override
    public List<Transaction> searchByCombination(String id, String name, String type) throws Exception {
        String string = "select t from Transaction t where ";
        int attributesNo = 0;
        if (!id.trim().isEmpty()) {
            attributesNo++;
            string += "t.transactionNo = :id and ";
        }
        if (!name.trim().isEmpty()) {
            attributesNo++;
            string += "upper(t.transactionName) LIKE upper('%" + name + "%') and ";
        }
        if (!type.trim().isEmpty()) {
            attributesNo++;
            string += "upper(t.type) LIKE upper('%" + type + "%') and ";
        }
        if (attributesNo > 0) {
            string = string.substring(0, string.length() - 4);
        } else {
            string = string.substring(0, string.length() - 6);
        }
        TypedQuery query = em.createQuery(string, Transaction.class);
        if (!id.trim().isEmpty())
            query.setParameter("id", Integer.valueOf(id.trim()));
        return query.getResultList();
    }

    @Override
    public void createTransaction(Transaction transaction) throws Exception {
        em.persist(transaction);
    }

    @Override
    public int searchNumberOfTransByUserId(int id) throws Exception {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery cq = qb.createQuery(Transaction.class);
        Root<Transaction> e = cq.from(Transaction.class);
        cq.select(e);
        Predicate predicate = qb.equal(e.get("user").get("uid").as(Integer.class), id);
        cq.where(predicate);
        TypedQuery tq = em.createQuery(cq);
        List<Transaction> transactions = tq.getResultList();
        if (transactions != null) {
            return transactions.size();
        }
        return 0;
    }
    
    
    
}
