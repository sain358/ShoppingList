package java2.shoppinglist.services.users.get;

public class GetUserRequest {

    private String login;
    private String password;

    public GetUserRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}