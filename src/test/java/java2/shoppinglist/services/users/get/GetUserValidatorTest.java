package java2.shoppinglist.services.users.get;

import java2.shoppinglist.domains.User;
import java2.shoppinglist.repositories.UserRepository;
import java2.shoppinglist.services.ShoppingListError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetUserValidatorTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetUserValidator validator = new GetUserValidator();

    private GetUserRequest request = new GetUserRequest("login", "password");

    @Test
    public void shouldNotReturnError() {
        Mockito.when(userRepository.findByLoginAndPassword(request.getLogin(), request.getPassword())).
                thenReturn(Optional.of(new User()));
        List<ShoppingListError> errors = validator.validate(request);

        assertEquals(0, errors.size());
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnUserCredentialsError() {
        Mockito.when(userRepository.findByLoginAndPassword(request.getLogin(), request.getPassword())).
                thenReturn(Optional.empty());
        List<ShoppingListError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("Wrong LOGIN or PASSWORD!", errors.get(0).getDescription());
    }

}