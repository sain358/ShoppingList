package java2.shoppinglist.services.products.add;


import java2.shoppinglist.domains.ShoppingList;

public class AddProductRequest {

    private String productTitle;
    private String productDescription;
    private Integer quantity;
    private ShoppingList shoppingList;

    public AddProductRequest(String productTitle, String productDescription, Integer quantity, ShoppingList shoppingList) {
        this.productTitle = productTitle;
        this.productDescription = productDescription;
        this.quantity = quantity;
        this.shoppingList = shoppingList;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }
}
