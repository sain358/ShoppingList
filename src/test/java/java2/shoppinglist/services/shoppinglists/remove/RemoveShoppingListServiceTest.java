package java2.shoppinglist.services.shoppinglists.remove;

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
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RemoveShoppingListServiceTest {

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @Mock
    private RemoveShoppingListValidator validator;

    @InjectMocks
    private RemoveShoppingListService service = new RemoveShoppingListService();

    private ShoppingList shoppingList = new ShoppingList();
    private RemoveShoppingListRequest request = new RemoveShoppingListRequest(new User(), "title");
    private List<ShoppingListError> errors = new ArrayList<>();

    @Before
    public void init() {
        shoppingList.setTitle(request.getTitle());
        shoppingList.setUser(request.getUser());
    }

    @Test
    public void shouldCallValidatorMethodValidate() {
        Mockito.when(shoppingListRepository.findByUserAndTitle(request.getUser(), request.getTitle())).
                thenReturn(Optional.of(shoppingList));
        service.execute(request);

        Mockito.verify(validator, Mockito.times(1)).
                validate(request);
    }

    @Test
    public void shouldCallRepositoryDeleteMethod() {
        Mockito.when(shoppingListRepository.findByUserAndTitle(request.getUser(), request.getTitle())).
                thenReturn(Optional.of(shoppingList));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        service.execute(request);

        Mockito.verify(shoppingListRepository, Mockito.times(1)).delete(shoppingList);
    }

    @Test
    public void shouldNotCallRepositoryDeleteMethod() {
        errors.add(new ShoppingListError("", ""));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        service.execute(request);

        Mockito.verify(shoppingListRepository, Mockito.times(0)).delete(shoppingList);
    }

}