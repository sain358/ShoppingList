package java2.shoppinglist.services.shoppinglists.remove;

import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.domains.User;
import java2.shoppinglist.repositories.ShoppingListRepository;
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
public class RemoveShoppingListValidatorTest {

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @InjectMocks
    private RemoveShoppingListValidator validator = new RemoveShoppingListValidator();

    private User user = new User();
    private RemoveShoppingListRequest request = new RemoveShoppingListRequest (user, "title");

    @Test
    public void shouldNotReturnError() {
        Mockito.when(shoppingListRepository.findByUserAndTitle(user, "title")).
                thenReturn(Optional.of(new ShoppingList()));
        List<ShoppingListError> errors = validator.validate(request);

        assertEquals(0, errors.size());
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnShoppingListExistenceError() {
        Mockito.when(shoppingListRepository.findByUserAndTitle(user, "title")).
                thenReturn(Optional.empty());
        List<ShoppingListError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("No such Shopping List found!", errors.get(0).getDescription());
    }

}