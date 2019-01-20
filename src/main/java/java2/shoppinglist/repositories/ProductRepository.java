package java2.shoppinglist.repositories;


import java2.shoppinglist.domains.Product;
import java2.shoppinglist.domains.ShoppingList;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    void addProduct(Product product);

    void removeProduct(Product product);

    Optional<Product> findByShoppingListAndTitle(ShoppingList shoppingList, String title);

    List<Product> getAllProducts(ShoppingList shoppingList);
}
