package java2.shoppinglist.services.shoppinglists.add;

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
public class AddShoppingListValidatorTest {

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @InjectMocks
    private AddShoppingListValidator validator;

    private User user = new User();
    private AddShoppingListRequest request = new AddShoppingListRequest(user, "title");

    @Test
    public void shouldNotReturnError() {
        Mockito.when(shoppingListRepository.findByUserAndTitle(user, "title")).
                thenReturn(Optional.empty());
        List<ShoppingListError> errors = validator.validate(request);

        assertEquals(0, errors.size());
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnDuplicateTitleError() {
        Mockito.when(shoppingListRepository.findByUserAndTitle(user, "title")).
                thenReturn(Optional.of(new ShoppingList()));
        List<ShoppingListError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("This Shopping List already exists!", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnEmptyTitleError() {
        Mockito.when(shoppingListRepository.findByUserAndTitle(user, "")).
                thenReturn(Optional.empty());
        request.setTitle("");
        List<ShoppingListError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("Empty title not allowed!", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnDuplicateTitleAndEmptyTitleError() {
        Mockito.when(shoppingListRepository.findByUserAndTitle(user, "")).
                thenReturn(Optional.of(new ShoppingList()));
        request.setTitle("");
        List<ShoppingListError> errors = validator.validate(request);

        assertEquals(2, errors.size());
        assertEquals("This Shopping List already exists!", errors.get(0).getDescription());
        assertEquals("Empty title not allowed!", errors.get(1).getDescription());
    }

}