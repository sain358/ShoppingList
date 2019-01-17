package java2.shoppinglist.services.products.remove.validation;

import java2.shoppinglist.database.ProductRepository;
import java2.shoppinglist.domains.Product;
import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.services.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NoSuchProductRule {

    @Autowired
    private ProductRepository db;

    public Optional<ShoppingListError> execute(ShoppingList shoppingList, String title) {
        Optional<Product> product = db.findByShoppingListAndTitle(shoppingList, title);
        if (!product.isPresent()) {
            ShoppingListError shoppingListError = new ShoppingListError("title", "No such product found!");
            return Optional.of(shoppingListError);
        }
        return Optional.empty();

    }


}
