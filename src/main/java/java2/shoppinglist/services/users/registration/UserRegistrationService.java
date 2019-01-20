package java2.shoppinglist.services.users.registration;

import java2.shoppinglist.repositories.UserRepository;
import java2.shoppinglist.domains.User;
import java2.shoppinglist.services.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class UserRegistrationService {

    @Autowired
    private UserRegistrationValidator validator;

    @Autowired
    private UserRepository userRepository;

    public UserRegistrationResponse execute(UserRegistrationRequest request) {

        UserRegistrationResponse response = new UserRegistrationResponse();

        List<ShoppingListError> validationErrors = validator.validate(request);
        response.setShoppingListErrors(validationErrors);
        if (!validationErrors.isEmpty()) {
            return response;
        }

        User user = new User();
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        userRepository.save(user);

        response.setUser(user);
        return response;
    }

}
