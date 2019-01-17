package java2.shoppinglist.database;


import java2.shoppinglist.domains.User;

import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findByLoginAndPassword(String login, String password);

    Optional<User> findByLogin(String login);

    Optional<User> findById(Long userId);

}
