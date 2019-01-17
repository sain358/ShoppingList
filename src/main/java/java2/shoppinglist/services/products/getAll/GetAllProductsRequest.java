package java2.shoppinglist.services.products.getAll;


import java2.shoppinglist.domains.ShoppingList;

public class GetAllProductsRequest {

    private ShoppingList shoppingList;

    public GetAllProductsRequest(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }
}
