package java2.shoppinglist.services.shoppinglists.remove;

import java2.shoppinglist.services.ShoppingListError;

import java.util.List;

public class RemoveShoppingListResponse {

    private List<ShoppingListError> shoppingListErrors;

    public RemoveShoppingListResponse(List<ShoppingListError> shoppingListErrors) {
        this.shoppingListErrors = shoppingListErrors;
    }

    public List<ShoppingListError> getShoppingListErrors() {
        return shoppingListErrors;
    }
}
