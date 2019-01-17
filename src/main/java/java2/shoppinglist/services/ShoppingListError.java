package java2.shoppinglist.services;

public class ShoppingListError {

    private String pointer;
    private String description;

    public ShoppingListError(String pointer, String description) {
        this.pointer = pointer;
        this.description = description;
    }

    public String getPointer() {
        return pointer;
    }

    public void setPointer(String pointer) {
        this.pointer = pointer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
