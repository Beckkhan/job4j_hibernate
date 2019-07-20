package ru.job4j.todo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.todo.models.Item;
import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 18.07.2019
 */
public class ToDoListService {

    private SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public void saveItem(Item item) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }

    public List<Item> findAll() {
        Session session = factory.openSession();
        session.beginTransaction();
        List<Item> result = session.createQuery("from Item", Item.class).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public void resetStatus(int id, boolean done) {
        Session session = factory.openSession();
        session.beginTransaction();
        Item item = session.get(Item.class, id);
        item.setDone(done);
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }
}