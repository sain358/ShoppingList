package java2.shoppinglist.services.products.getAll.validation;

import java2.shoppinglist.services.ShoppingListError;
import java2.shoppinglist.services.products.getAll.GetAllProductsRequest;
import java2.shoppinglist.services.products.getAll.validation.EmptyShoppingListRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllProductsValidator {

    @Autowired
    private EmptyShoppingListRule emptyShoppingListRule;

    public List<ShoppingListError> validate(GetAllProductsRequest request) {
        List<ShoppingListError> shoppingListErrors = new ArrayList<>();

        emptyShoppingListRule.execute(request.getShoppingList()).ifPresent(shoppingListErrors::add);

        return shoppingListErrors;
    }

}
