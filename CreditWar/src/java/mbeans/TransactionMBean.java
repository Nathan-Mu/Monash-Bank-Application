package mbeans;

import entities.Transaction;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import repository.TransactionRepository;

/**
 *
 * @author nathan
 */
@ManagedBean(name = "transactionController")
@SessionScoped

public class TransactionMBean {
    @EJB
    private TransactionRepository transactionRepository;
    
    private String transactionNo;
    
    public TransactionMBean() {
        
    }
    
    public Transaction getTransaction() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        transactionNo =  params.get("transactionNo"); 
        return findTransactionById(Integer.valueOf(transactionNo));
    }
    
    public Transaction findTransactionById(int id) {
        try {
            return transactionRepository.searchTransactionById(id);
        } catch (Exception e) {
            Logger.getLogger(CreditMBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return new Transaction();
    }
}
