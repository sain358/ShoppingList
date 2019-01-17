package java2.shoppinglist.services.shoppinglists.getAll;

import java2.shoppinglist.domains.User;

public class GetAllShoppingListsRequest {

    private User user;

    public GetAllShoppingListsRequest(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
