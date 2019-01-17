package java2.shoppinglist.database;

import java2.shoppinglist.database.UserRepository;
import java2.shoppinglist.domains.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        String query = "from User u where u.login = :login and u.password = :password";
        User user = (User) sessionFactory.getCurrentSession().createQuery(query)
                .setParameter("login", login)
                .setParameter("password", password)
                .uniqueResult();
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        String query = "from User u where u.login = :login";
        User user = (User) sessionFactory.getCurrentSession().createQuery(query)
                .setParameter("login", login)
                .uniqueResult();
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findById(Long userId) {
        String query = "from User u where u.id = :id";
        User user = (User) sessionFactory.getCurrentSession().createQuery(query)
                .setParameter("id", userId)
                .uniqueResult();
        return Optional.ofNullable(user);
    }

}
