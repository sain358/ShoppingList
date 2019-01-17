package java2.shoppinglist.services.shoppinglists.getAll;


import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.services.ShoppingListError;

import java.util.List;

public class GetAllShoppingListsResponse {

    private List<ShoppingList> shoppingLists;
    private List<ShoppingListError> shoppingListErrors;

    public GetAllShoppingListsResponse(List<ShoppingList> shoppingLists, List<ShoppingListError> shoppingListErrors) {
        this.shoppingLists = shoppingLists;
        this.shoppingListErrors = shoppingListErrors;
    }

    public List<ShoppingList> getShoppingLists() {
        return shoppingLists;
    }

    public List<ShoppingListError> getShoppingListErrors() {
        return shoppingListErrors;
    }
}
