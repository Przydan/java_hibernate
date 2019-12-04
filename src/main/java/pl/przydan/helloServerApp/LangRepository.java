package pl.przydan.helloServerApp;

import java.util.Optional;

import static pl.przydan.helloServerApp.HibernateUtil.getSessionFactory;

public class LangRepository {
    Optional<Lang> findById(Integer id) {
        var session = getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.get(Lang.class, id);
        transaction.commit();
        session.close();
        return Optional.ofNullable(result);
    }
}
