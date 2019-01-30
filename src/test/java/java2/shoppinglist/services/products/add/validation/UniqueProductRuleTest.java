package java2.shoppinglist.services.products.add.validation;

import java2.shoppinglist.domains.Product;
import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.repositories.ProductRepository;
import java2.shoppinglist.services.ShoppingListError;
import java2.shoppinglist.services.products.add.AddProductRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UniqueProductRuleTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private UniqueProductRule rule = new UniqueProductRule();

    private ShoppingList shoppingList = new ShoppingList();

    @Test
    public void shouldNotReturnErrorIfProductIsUnique() {
        Mockito.when(productRepository.findByShoppingListAndTitle(shoppingList, "title")).
                thenReturn(Optional.empty());

        Optional<ShoppingListError> error = rule.execute(new AddProductRequest(
                "title", "description", 2, shoppingList));

        assertFalse(error.isPresent());
    }

    @Test
    public void shouldReturnErrorIfProductIsNotUnique() {
        Mockito.when(productRepository.findByShoppingListAndTitle(shoppingList, "title")).
                thenReturn(Optional.of(new Product()));

        Optional<ShoppingListError> error = rule.execute(new AddProductRequest(
                "title", "description", 1, shoppingList));

        assertTrue(error.isPresent());
        assertEquals("product", error.get().getPointer());
        assertEquals("This product already exists!", error.get().getDescription());
    }

}