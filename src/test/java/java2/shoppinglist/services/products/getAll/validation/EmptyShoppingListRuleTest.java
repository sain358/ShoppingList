package java2.shoppinglist.services.products.getAll.validation;

import java2.shoppinglist.domains.Product;
import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.repositories.ProductRepository;
import java2.shoppinglist.services.ShoppingListError;
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
public class EmptyShoppingListRuleTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private EmptyShoppingListRule rule = new EmptyShoppingListRule();

    private ShoppingList shoppingList = new ShoppingList();

    @Test
    public void shouldReturnErrorIfShoppingListIsEmpty() {
        Mockito.when(productRepository.getAllByShoppingList(shoppingList)).thenReturn(new ArrayList<Product>());
        Optional<ShoppingListError> error = rule.execute(shoppingList);
        assertTrue(error.isPresent());
    }

    @Test
    public void shouldNotReturnErrorIfShoppingListIsNotEmpty() {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product());
        Mockito.when(productRepository.getAllByShoppingList(shoppingList)).thenReturn(products);
        Optional<ShoppingListError> error = rule.execute(shoppingList);
        assertFalse(error.isPresent());
    }

}