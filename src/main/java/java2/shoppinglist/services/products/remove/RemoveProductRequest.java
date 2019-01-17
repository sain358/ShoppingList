package java2.shoppinglist.services.products.remove;

import java2.shoppinglist.domains.ShoppingList;

public class RemoveProductRequest {
    private ShoppingList shoppingList;
    private String productTitle;

    public RemoveProductRequest(ShoppingList shoppingList, String productTitle) {
        this.shoppingList = shoppingList;
        this.productTitle = productTitle;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public String getProductTitle() {
        return productTitle;
    }
}
