package pl.przydan.helloServerApp.todo;

import java.util.List;
import java.util.Optional;

import static pl.przydan.helloServerApp.HibernateUtil.getSessionFactory;

public class TodoRepository {
    List<Todo> findAll() {
        var session = getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var result = session.createQuery("from Todo", Todo.class).list();

        transaction.commit();
        session.close();
        return result;
    }

    public Optional<Todo> findById(Integer id) {
        var session = getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var result = Optional.ofNullable(session.get(Todo.class, id));

        transaction.commit();
        session.close();
        return result;
    }
}
