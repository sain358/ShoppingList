package java2.shoppinglist.services.products.remove;

import java2.shoppinglist.services.ShoppingListError;

import java.util.List;

public class RemoveProductResponse {

    private List<ShoppingListError> shoppingListErrors;

    public RemoveProductResponse(List<ShoppingListError> shoppingListErrors) {
        this.shoppingListErrors = shoppingListErrors;
    }

    public List<ShoppingListError> getShoppingListErrors() {
        return shoppingListErrors;
    }

}
