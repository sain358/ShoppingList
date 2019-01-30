package java2.shoppinglist.services.products.getAll;

import java2.shoppinglist.repositories.ProductRepository;
import java2.shoppinglist.domains.Product;
import java2.shoppinglist.services.ShoppingListError;
import java2.shoppinglist.services.products.getAll.validation.GetAllProductsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetAllProductsService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GetAllProductsValidator validator;

    public GetAllProductsResponse execute(GetAllProductsRequest request) {
        List<Product> products = productRepository.getAllByShoppingList(request.getShoppingList());
        List<ShoppingListError> shoppingListErrors = validator.validate(request);
        return new GetAllProductsResponse(products, shoppingListErrors);
    }
}
