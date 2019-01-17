package java2.shoppinglist.services.products.add.validation;

import java2.shoppinglist.services.ShoppingListError;
import java2.shoppinglist.services.products.add.AddProductRequest;
import java2.shoppinglist.services.products.add.validation.EmptyTitleRule;
import java2.shoppinglist.services.products.add.validation.UniqueProductRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddProductValidator {

    @Autowired
    private EmptyTitleRule emptyTitleRule;

    @Autowired
    private UniqueProductRule uniqueProductRule;

    public List<ShoppingListError> validate(AddProductRequest addProductRequest) {
        List<ShoppingListError> shoppingListErrors = new ArrayList<>();

        emptyTitleRule.execute(addProductRequest.getProductTitle()).ifPresent(shoppingListErrors::add);
        uniqueProductRule.execute(addProductRequest).ifPresent(shoppingListErrors::add);

        return shoppingListErrors;
    }
}
