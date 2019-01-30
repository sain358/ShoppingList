package java2.shoppinglist.services.users.registration;

import java2.shoppinglist.domains.User;
import java2.shoppinglist.repositories.UserRepository;
import java2.shoppinglist.services.ShoppingListError;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationServiceTest {

    @Mock
    private UserRegistrationValidator validator;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserRegistrationService service = new UserRegistrationService();

    private UserRegistrationRequest request = new UserRegistrationRequest(
            "login", "password", "password");
    private List<ShoppingListError> errors = new ArrayList<>();
    private User user = new User();

    @Before
    public void init() {
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
    }

    @Test
    public void shouldCallValidatorMethodValidate() {
        service.execute(request);

        Mockito.verify(validator, Mockito.times(1)).
                validate(request);
    }

    @Test
    public void shouldCallRepositorySaveMethod() {
        Mockito.when(validator.validate(request)).thenReturn(errors);
        service.execute(request);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    public void shouldNotCallRepositorySaveMethod() {
        errors.add(new ShoppingListError("", ""));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        service.execute(request);

        Mockito.verify(userRepository, Mockito.times(0)).save(user);
    }

}