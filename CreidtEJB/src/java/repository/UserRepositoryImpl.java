/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entities.Transaction;
import entities.User;
import java.math.BigDecimal;
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
public class UserRepositoryImpl implements UserRepository{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findAllUsers() throws Exception {
        Query query = em.createNamedQuery("User.findAll");
        return query.getResultList();
    }

    @Override
    public User searchUserById(int id) throws Exception {
        User user = em.find(User.class, id);
        return user;
    }

    @Override
    public void removeUser(int id) throws Exception {
        User user = this.searchUserById(id);
        if (user != null) {
            em.remove(user);
        }
    }

    @Override
    public void createUser(String fname, String lname, String type, String phoneNo, String email, String address, String password) throws Exception {
        User user = new User(fname, lname, email, password, type, address, phoneNo);
        em.persist(user);
    }

    @Override
    public void editUser(User user) throws Exception {
        em.merge(user);
    }

    @Override
    public boolean searchUserExistById(int id) throws Exception {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery cq = qb.createQuery(User.class);
        Root<User> e = cq.from(User.class);
        cq.select(e);
        Predicate predicate = qb.equal(e.get("uid").as(Integer.class), id);
        cq.where(predicate);
        TypedQuery tq = em.createQuery(cq);
        List<User> users = tq.getResultList();
        if (users != null) {
            if (users.size() > 0)
                return true;
        }
        return false;
    }

    @Override
    public BigDecimal searchBalanceById(int id) throws Exception {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery cq = qb.createQuery(User.class);
        Root<Transaction> e = cq.from(User.class);
        cq.select(e);
        Predicate predicate = qb.equal(e.get("uid").as(Integer.class), id);
        cq.where(predicate);
        TypedQuery tq = em.createQuery(cq);
        User user = (User) tq.getSingleResult();
        if (user != null) {
            if (user.getUid() > 0) {
                return user.getBalance();
            }
        }
        return BigDecimal.ZERO;
    }
}
