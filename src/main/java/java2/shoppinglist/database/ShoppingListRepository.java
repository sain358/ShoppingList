package java2.shoppinglist.database;


import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.domains.User;

import java.util.List;
import java.util.Optional;

public interface ShoppingListRepository {

    void save(ShoppingList shoppingList);

    void remove(ShoppingList shoppingList);

    Optional<ShoppingList> findByUserAndTitle(User user, String title);

    List<ShoppingList> findShoppingLists(User user);

}