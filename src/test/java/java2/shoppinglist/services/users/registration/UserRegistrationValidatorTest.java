package java2.shoppinglist.services.users.registration;

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
public class UserRegistrationValidatorTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserRegistrationValidator validator = new UserRegistrationValidator();

    private UserRegistrationRequest request = new UserRegistrationRequest(
            "login", "password", "password");

    @Test
    public void shouldNotReturnError() {
        Mockito.when(userRepository.findByLogin(request.getLogin())).
                thenReturn(Optional.empty());
        List<ShoppingListError> errors = validator.validate(request);

        assertEquals(0, errors.size());
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnEmptyLoginError() {
        request.setLogin("");
        Mockito.when(userRepository.findByLogin(request.getLogin())).
                thenReturn(Optional.empty());
        List<ShoppingListError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("Empty login not allowed!", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnEmptyPasswordError() {
        request.setPassword("");
        Mockito.when(userRepository.findByLogin(request.getLogin())).
                thenReturn(Optional.empty());
        List<ShoppingListError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("Empty password not allowed!", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnRepeatedPasswordMismatchError() {
        request.setRepeatedPassword("");
        Mockito.when(userRepository.findByLogin(request.getLogin())).
                thenReturn(Optional.empty());
        List<ShoppingListError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("Incorrect repeated password!", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnDuplicateLoginError() {
        Mockito.when(userRepository.findByLogin(request.getLogin())).
                thenReturn(Optional.of(new User()));
        List<ShoppingListError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("This user already exists!", errors.get(0).getDescription());
    }

}