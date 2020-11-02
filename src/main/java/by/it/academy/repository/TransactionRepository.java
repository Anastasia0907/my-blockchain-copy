package by.it.academy.repository;

import by.it.academy.pojo.Transaction;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class TransactionRepository implements GenericDao<Transaction> {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void create(Transaction transaction) {
        sessionFactory
                .getCurrentSession()
                .saveOrUpdate(transaction);
    }

    @Override
    public void update(Transaction transaction) {
        create(transaction);
    }

    @Override
    public Transaction read(Serializable id) {
        return sessionFactory
                .getCurrentSession()
                .get(Transaction.class, id);
    }

    @Override
    public void delete(Transaction transaction) {
        sessionFactory
                .getCurrentSession()
                .delete(transaction);
    }

    @Override
    public List<Transaction> findAll() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Transaction", Transaction.class)
                .list();
    }

    public List<Transaction> findInputsByRecipientPublicKey(String publicKey) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Transaction t where t.recipientPublicKeyString=:publicKey", Transaction.class)
                .setParameter("publicKey", publicKey)
                .list();
    }

    public List<Transaction> findOutputsBySenderPublicKey(String publicKey) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Transaction t where t.senderPublicKeyString=:publicKey", Transaction.class)
                .setParameter("publicKey", publicKey)
                .list();
    }
}
