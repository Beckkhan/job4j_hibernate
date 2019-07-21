package ru.job4j.todo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.job4j.todo.models.Item;
import java.util.List;
import java.util.function.Function;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 2.0
 * @since 21.07.2019
 */
public class ToDoListService {

    private SessionFactory factory = new Configuration().configure().buildSessionFactory();

    private <T> T tx(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T result = command.apply(session);
            tx.commit();
            return result;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void saveItem(Item item) {
        this.tx(
                session -> session.save(item)
        );
    }

    public List<Item> findAll() {
        return this.tx(
                session -> session.createQuery("from Item", Item.class).list()
        );
    }

    public void resetStatus(int id, boolean done) {
        this.tx(
                session -> {
                    Item item = session.get(Item.class, id);
                    item.setDone(done);
                    session.update(item);
                    return true;
                }
        );
    }
}