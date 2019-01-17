package java2.shoppinglist.services.shoppinglists.add;


import java2.shoppinglist.domains.User;

public class AddShoppingListRequest {

    private User user;
    private String title;

    public AddShoppingListRequest(User user, String title) {
        this.user = user;
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
