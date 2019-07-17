package ru.job4j.introduction;

import ru.job4j.introduction.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.Calendar;
import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 17.07.2019
 */
public class HibernateRun {

    private SessionFactory factory;

    public HibernateRun() {
        this.factory = new Configuration()
                .configure()
                .buildSessionFactory();
    }

    public static void main(String[] args) {
        HibernateRun hibernateRun = new HibernateRun();
        User user = new User();
        user.setName("First");
        user.setExpired(Calendar.getInstance());
        int userId = hibernateRun.createOrUpdate(user);
        user = hibernateRun.findUser(userId);
        System.out.println(user);
        user.setName("First_Updated");
        hibernateRun.createOrUpdate(user);
        user = hibernateRun.findUser(userId);
        System.out.println(user);
        hibernateRun.deleteUser(user);
        List<User> users = hibernateRun.findAll();
        System.out.println(users);
    }

    public int createOrUpdate(User user) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(user);
            tx.commit();
            return user.getId();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                throw e;
            }
        }
        throw new IllegalStateException("Create Or Update Error.");
    }

    public User findUser(int id) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            User user = session.get(User.class, id);
            tx.commit();
            return user;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                throw e;
            }
        }
        throw new IllegalStateException("Search Failed.");
    }

    public void deleteUser(User user) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                throw e;
            }
        }
    }

    public List<User> findAll() {
        List<User> res = null;
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            res = session.createQuery("from User", User.class).list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                throw e;
            }
        }
        return res;
    }
}