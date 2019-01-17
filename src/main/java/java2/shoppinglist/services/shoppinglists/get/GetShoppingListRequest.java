package java2.shoppinglist.services.shoppinglists.get;


import java2.shoppinglist.domains.User;

public class GetShoppingListRequest {

    private User user;
    private String title;

    public GetShoppingListRequest(User user, String title) {
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
