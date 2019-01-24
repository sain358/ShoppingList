package java2.shoppinglist.repositories;


import java2.shoppinglist.domains.Product;
import java2.shoppinglist.domains.ShoppingList;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findByShoppingListAndTitle(ShoppingList shoppingList, String title);

    List<Product> getAllByShoppingList(ShoppingList shoppingList);

}
