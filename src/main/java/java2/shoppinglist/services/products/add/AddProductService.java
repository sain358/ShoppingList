package java2.shoppinglist.services.products.add;

import java2.shoppinglist.database.ProductRepository;
import java2.shoppinglist.domains.Product;
import java2.shoppinglist.services.ShoppingListError;
import java2.shoppinglist.services.products.add.validation.AddProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class AddProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AddProductValidator validator;

    public AddProductResponse execute(AddProductRequest request) {
        Product product = new Product();
        List<ShoppingListError> shoppingListErrors = validator.validate(request);
        if (!shoppingListErrors.isEmpty()) {
            return new AddProductResponse(product, shoppingListErrors);
        }
        product.setTitle(request.getProductTitle());
        product.setDescription(request.getProductDescription());
        product.setQuantity(request.getQuantity());
        product.setShoppingList(request.getShoppingList());
        productRepository.addProduct(product);
        return new AddProductResponse(product, shoppingListErrors);
    }

}
