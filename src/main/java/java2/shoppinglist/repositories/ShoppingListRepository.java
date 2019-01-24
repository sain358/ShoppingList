package java2.shoppinglist.repositories;


import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.domains.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ShoppingListRepository extends CrudRepository<ShoppingList, Long> {

    Optional<ShoppingList> findByUserAndTitle(User user, String title);

    List<ShoppingList> findAllByUser(User user);

}