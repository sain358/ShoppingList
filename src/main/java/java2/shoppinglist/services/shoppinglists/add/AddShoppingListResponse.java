package java2.shoppinglist.services.shoppinglists.add;

import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.services.ShoppingListError;

import java.util.List;

public class AddShoppingListResponse {

    private ShoppingList shoppingList;
    private List<ShoppingListError> shoppingListErrors;


    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public List<ShoppingListError> getShoppingListErrors() {
        return shoppingListErrors;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public void setShoppingListErrors(List<ShoppingListError> shoppingListErrors) {
        this.shoppingListErrors = shoppingListErrors;
    }
}
