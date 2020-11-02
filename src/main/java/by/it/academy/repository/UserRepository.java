package by.it.academy.repository;

import by.it.academy.pojo.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class UserRepository implements GenericDao<User> {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void create(User user) {
        sessionFactory
                .getCurrentSession()
                .saveOrUpdate(user);
    }

    @Override
    public void update(User user) {
        sessionFactory
                .getCurrentSession()
                .saveOrUpdate(user);
    }

    @Override
    public User read(Serializable id) {
        return sessionFactory
                .getCurrentSession()
                .get(User.class, id);
    }

    @Override
    public void delete(User user) {
        sessionFactory
                .getCurrentSession()
                .delete(user);
    }

    public User find(String userName) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from User u where u.userName=:userName", User.class)
                .setParameter("userName", userName)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> findAll() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from User", User.class)
                .list();
    }

}
