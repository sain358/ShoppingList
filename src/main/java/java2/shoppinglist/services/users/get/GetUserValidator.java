package java2.shoppinglist.services.users.get;

import java2.shoppinglist.repositories.UserRepository;
import java2.shoppinglist.domains.User;
import java2.shoppinglist.services.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetUserValidator {

    @Autowired
    private UserRepository userRepository;

    public List<ShoppingListError> validate(GetUserRequest request) {
        List<ShoppingListError> errors = new ArrayList<>();

        Optional<ShoppingListError> errorOptional = validateUserCredentials(request.getLogin(), request.getPassword());
        errorOptional.ifPresent(errors::add);

        return errors;
    }

    private Optional<ShoppingListError> validateUserCredentials(String login, String password) {

        Optional<User> userOptional = userRepository.findByLoginAndPassword(login, password);

        if (!userOptional.isPresent()) {
            return Optional.of(new ShoppingListError("authorisation", "Wrong LOGIN or PASSWORD!"));
        }
        return Optional.empty();

    }

}
