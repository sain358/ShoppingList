package java2.shoppinglist.services.shoppinglists.getAll;

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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetAllShoppingListsValidatorTest {

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @InjectMocks
    private GetAllShoppingListsValidator validator = new GetAllShoppingListsValidator();

    private User user = new User();
    private GetAllShoppingListsRequest request = new GetAllShoppingListsRequest(user);
    private List<ShoppingList> shoppingLists = new ArrayList<>();

    @Test
    public void shouldNotReturnError() {
        shoppingLists.add(new ShoppingList());
        Mockito.when(shoppingListRepository.findAllByUser(user)).
                thenReturn(shoppingLists);
        List<ShoppingListError> errors = validator.validate(request);

        assertEquals(0, errors.size());
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnEmptyShoppingListError() {
        Mockito.when(shoppingListRepository.findAllByUser(user)).
                thenReturn(shoppingLists);
        List<ShoppingListError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("There are no Shopping Lists!", errors.get(0).getDescription());
    }

}