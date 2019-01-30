package java2.shoppinglist.services.products.remove.validation;

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
public class NoSuchProductRuleTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private NoSuchProductRule rule = new NoSuchProductRule();

    private ShoppingList shoppingList = new ShoppingList();

    @Test
    public void shouldNotReturnErrorIfProductFound() {
        Mockito.when(productRepository.findByShoppingListAndTitle(shoppingList, "title")).
                thenReturn(Optional.of(new Product()));

        Optional<ShoppingListError> error = rule.execute(shoppingList, "title");

        assertFalse(error.isPresent());
    }

    @Test
    public void shouldReturnErrorIfProductNotFound() {
        Mockito.when(productRepository.findByShoppingListAndTitle(shoppingList, "title")).
                thenReturn(Optional.empty());

        Optional<ShoppingListError> error = rule.execute(shoppingList, "title");

        assertTrue(error.isPresent());
        assertEquals("product", error.get().getPointer());
        assertEquals("No such product found!", error.get().getDescription());
    }
}