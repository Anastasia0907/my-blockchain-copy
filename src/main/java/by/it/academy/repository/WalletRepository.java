package by.it.academy.repository;

import by.it.academy.pojo.User;
import by.it.academy.pojo.Wallet;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.security.PublicKey;
import java.util.List;

@Repository
public class WalletRepository implements GenericDao<Wallet>{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void create(Wallet wallet) {
        sessionFactory
                .getCurrentSession()
                .saveOrUpdate(wallet);
    }

    @Override
    public void update(Wallet wallet) {
        sessionFactory
                .getCurrentSession()
                .saveOrUpdate(wallet);
    }

    @Override
    public Wallet read(Serializable id) {
        return sessionFactory
                .getCurrentSession()
                .get(Wallet.class, id);
    }

    @Override
    public void delete(Wallet wallet) {
        sessionFactory.getCurrentSession().delete(wallet);
    }

    public Wallet find(PublicKey publicKey) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Wallet w where w.publicKey=:publicKey", Wallet.class)
                .setParameter("publicKey", publicKey)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Wallet> findAll() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Wallet", Wallet.class)
                .list();
    }

    public Wallet findByPublicKeyString(String publicKeyString) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Wallet w where w.publicKeyString=:publicKeyString", Wallet.class)
                .setParameter("publicKeyString", publicKeyString)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public List<Wallet> findAllByUser(User user) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Wallet w where w.walletOwner=:user", Wallet.class)
                .setParameter("user", user)
                .list();
    }
}
