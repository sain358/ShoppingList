package java2.shoppinglist.services.shoppinglists.getAll;

import java2.shoppinglist.domains.User;
import java2.shoppinglist.repositories.ShoppingListRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetAllShoppingListsServiceTest {

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @Mock
    private GetAllShoppingListsValidator validator;

    @InjectMocks
    private GetAllShoppingListsService service = new GetAllShoppingListsService();

    private User user = new User();
    private GetAllShoppingListsRequest request = new GetAllShoppingListsRequest(user);

    @Test
    public void shouldCallValidatorMethodValidate() {
        service.execute(request);
        Mockito.verify(validator, Mockito.times(1)).validate(request);
    }

    @Test
    public void shouldCallRepositoryMethodFindAllByUser() {
        service.execute(request);
        Mockito.verify(shoppingListRepository, Mockito.times(1)).findAllByUser(user);
    }

}