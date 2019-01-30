package java2.shoppinglist.services.products.add.validation;

import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.services.ShoppingListError;
import java2.shoppinglist.services.products.add.AddProductRequest;
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
public class AddProductValidatorTest {

    @Mock
    private EmptyTitleRule emptyTitleRule;

    @Mock
    private UniqueProductRule uniqueProductRule;

    @InjectMocks
    private AddProductValidator validator = new AddProductValidator();

    private AddProductRequest request = new AddProductRequest (
            "title", "description", 1, new ShoppingList());

    @Test
    public void shouldNotReturnListOfErrorsIfThereIsNoAnyError() {
        Mockito.when(emptyTitleRule.execute("title")).
                thenReturn(Optional.empty());

        Mockito.when(uniqueProductRule.execute(request)).
                thenReturn(Optional.empty());

        List<ShoppingListError> errors = validator.validate(request);

        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnListOfErrorsIfThereIsSomeError() {
        Mockito.when(emptyTitleRule.execute("title")).
                thenReturn(Optional.empty());

        Mockito.when(uniqueProductRule.execute(request)).
                thenReturn(Optional.of(new ShoppingListError("pointer", "description")));

        List<ShoppingListError> errors = validator.validate(request);

        assertEquals(1, errors.size());
    }

}