package java2.shoppinglist.services.products.remove.validation;

import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.services.ShoppingListError;
import java2.shoppinglist.services.products.getAll.GetAllProductsRequest;
import java2.shoppinglist.services.products.remove.RemoveProductRequest;
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
public class RemoveProductValidatorTest {

    @Mock
    private NoSuchProductRule noSuchProductRule;

    @InjectMocks
    private RemoveProductValidator validator = new RemoveProductValidator();

    private ShoppingList shoppingList = new ShoppingList();

    @Test
    public void shouldNotReturnListOfErrorsIfThereIsNoAnyError() {
        Mockito.when(noSuchProductRule.execute(new ShoppingList(), "title")).
                thenReturn(Optional.empty());

        List<ShoppingListError> errors = validator.validate(new RemoveProductRequest(shoppingList, "title"));

        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnListOfErrorsIfThereIsSomeError() {
        Mockito.when(noSuchProductRule.execute(new ShoppingList(), "title")).
                thenReturn(Optional.of(new ShoppingListError("pointer", "description")));

        List<ShoppingListError> errors = validator.validate(new RemoveProductRequest(shoppingList, "title"));

        assertEquals(1, errors.size());
    }

}