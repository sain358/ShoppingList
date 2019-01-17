package java2.shoppinglist.services.products.getAll.validation;

import java2.shoppinglist.database.ProductRepository;
import java2.shoppinglist.domains.Product;
import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.services.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmptyShoppingListRule {

    @Autowired
    private ProductRepository db;

    public Optional<ShoppingListError> execute(ShoppingList shoppingList) {
        List<Product> products = db.getAllProducts(shoppingList);
        if (products.isEmpty()) {
            ShoppingListError shoppingListError = new ShoppingListError("", "Shopping list is empty!");
            return Optional.of(shoppingListError);
        }
        return Optional.empty();
    }


}
