package pl.przydan.helloServerApp.lang;

import java.util.List;
import java.util.Optional;

import static pl.przydan.helloServerApp.HibernateUtil.getSessionFactory;

public class LangRepository {
    List<Lang> findAll() {
        var session = getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var result = session.createQuery("from Lang", Lang.class).list();

        transaction.commit();
        session.close();
        return result;
    }

    public Optional<Lang> findById(Integer id) {
        var session = getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var result = Optional.ofNullable(session.get(Lang.class, id));

        transaction.commit();
        session.close();
        return result;
    }
}
