package java2.shoppinglist.services.shoppinglists.add;

import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.domains.User;
import java2.shoppinglist.repositories.ShoppingListRepository;
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
public class AddShoppingListServiceTest {

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @Mock
    private AddShoppingListValidator validator;

    @InjectMocks
    private AddShoppingListService service = new AddShoppingListService();

    private ShoppingList shoppingList = new ShoppingList();
    private AddShoppingListRequest request = new AddShoppingListRequest(new User(), "title");
    private List<ShoppingListError> errors = new ArrayList<>();

    @Before
    public void init() {
        shoppingList.setTitle(request.getTitle());
        shoppingList.setUser(request.getUser());
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

        Mockito.verify(shoppingListRepository, Mockito.times(1)).save(shoppingList);
    }

    @Test
    public void shouldNotCallRepositorySaveMethod() {
        errors.add(new ShoppingListError("", ""));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        service.execute(request);

        Mockito.verify(shoppingListRepository, Mockito.times(0)).save(shoppingList);
    }

}