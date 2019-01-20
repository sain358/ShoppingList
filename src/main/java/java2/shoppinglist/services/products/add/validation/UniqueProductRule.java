package java2.shoppinglist.services.products.add.validation;

import java2.shoppinglist.repositories.ProductRepository;
import java2.shoppinglist.domains.Product;
import java2.shoppinglist.services.ShoppingListError;
import java2.shoppinglist.services.products.add.AddProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UniqueProductRule {

    @Autowired
    private ProductRepository productRepository;

    public Optional<ShoppingListError> execute(AddProductRequest request) {

        Optional<Product> productOptional = productRepository.findByShoppingListAndTitle(
                request.getShoppingList(), request.getProductTitle());

        if (productOptional.isPresent()) {
            ShoppingListError shoppingListError = new ShoppingListError("product", "This product already exists!");
            return Optional.of(shoppingListError);
        }
        return Optional.empty();

    }


}
