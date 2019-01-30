package java2.shoppinglist.services.users.get;

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
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private GetUserValidator validator;

    @InjectMocks
    private GetUserService service = new GetUserService();

    private GetUserRequest request = new GetUserRequest("login", "password");
    private List<ShoppingListError> errors = new ArrayList<>();
    private User user = new User();

    @Before
    public void init() {
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
    }

    @Test
    public void shouldCallValidatorMethodValidate() {
        Mockito.when(userRepository.findByLoginAndPassword(request.getLogin(), request.getPassword())).
                thenReturn(Optional.of(user));
        service.execute(request);

        Mockito.verify(validator, Mockito.times(1)).
                validate(request);
    }

    @Test
    public void shouldCallRepositoryFindByLoginAndPasswordMethod() {
        Mockito.when(userRepository.findByLoginAndPassword(request.getLogin(), request.getPassword())).
                thenReturn(Optional.of(user));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        service.execute(request);

        Mockito.verify(userRepository, Mockito.times(1)).
                findByLoginAndPassword(request.getLogin(), request.getPassword());
    }

    @Test
    public void shouldNotCallRepositoryFindByLoginAndPasswordMethod() {
        Mockito.when(userRepository.findByLoginAndPassword(request.getLogin(), request.getPassword())).
                thenReturn(Optional.of(user));

        errors.add(new ShoppingListError("", ""));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        service.execute(request);

        Mockito.verify(userRepository, Mockito.times(0)).
                findByLoginAndPassword(request.getLogin(), request.getPassword());
    }

}