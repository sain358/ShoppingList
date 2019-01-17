package java2.shoppinglist.services.products.remove.validation;

import java2.shoppinglist.services.ShoppingListError;
import java2.shoppinglist.services.products.remove.RemoveProductRequest;
import java2.shoppinglist.services.products.remove.validation.NoSuchProductRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RemoveProductValidator {

    @Autowired
    private NoSuchProductRule noSuchProductRule;

    public List<ShoppingListError> validate(RemoveProductRequest removeProductRequest) {
        List<ShoppingListError> shoppingListErrors = new ArrayList<>();

        noSuchProductRule.execute(removeProductRequest.getShoppingList(), removeProductRequest.getProductTitle()).ifPresent(shoppingListErrors::add);

        return shoppingListErrors;
    }


}
