package java2.shoppinglist.services.products.getAll.validation;

import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.services.ShoppingListError;
import java2.shoppinglist.services.products.getAll.GetAllProductsRequest;
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
public class GetAllProductsValidatorTest {

    @Mock
    private EmptyShoppingListRule emptyShoppingListRule;

    @InjectMocks
    private GetAllProductsValidator validator = new GetAllProductsValidator();

    private ShoppingList shoppingList = new ShoppingList();


    @Test
    public void shouldNotReturnListOfErrorsIfThereIsNoAnyError() {
        Mockito.when(emptyShoppingListRule.execute(shoppingList)).
                thenReturn(Optional.empty());

        List<ShoppingListError> errors = validator.validate(new GetAllProductsRequest(shoppingList));

        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnListOfErrorsIfThereIsSomeError() {
        Mockito.when(emptyShoppingListRule.execute(shoppingList)).
                thenReturn(Optional.of(new ShoppingListError("pointer", "description")));

        List<ShoppingListError> errors = validator.validate(new GetAllProductsRequest(shoppingList));

        assertEquals(1, errors.size());
    }

}