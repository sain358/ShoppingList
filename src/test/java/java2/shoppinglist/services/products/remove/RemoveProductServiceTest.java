package java2.shoppinglist.services.products.remove;

import java2.shoppinglist.domains.Product;
import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.repositories.ProductRepository;
import java2.shoppinglist.services.ShoppingListError;
import java2.shoppinglist.services.products.remove.validation.RemoveProductValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class RemoveProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private RemoveProductValidator validator;

    @InjectMocks
    private RemoveProductService service = new RemoveProductService();

    private RemoveProductRequest request = new RemoveProductRequest(new ShoppingList(), "title");
    private Product product = new Product();
    private List<ShoppingListError> errors = new ArrayList<>();

    @Test
    public void shouldCallValidatorMethodValidate() {
        Mockito.when(productRepository.findByShoppingListAndTitle(request.getShoppingList(), request.getProductTitle())).
                thenReturn(Optional.of(product));
        service.execute(request);
        Mockito.verify(validator, Mockito.times(1)).
                validate(request);
    }

    @Test
    public void shouldCallRepositoryDeleteMethod() {
        Mockito.when(productRepository.findByShoppingListAndTitle(request.getShoppingList(), request.getProductTitle())).
                thenReturn(Optional.of(product));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        service.execute(request);
        Mockito.verify(productRepository, Mockito.times(1)).delete(product);
    }

    @Test
    public void shouldNotCallRepositoryDeleteMethod() {
        errors.add(new ShoppingListError("", ""));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        service.execute(request);
        Mockito.verify(productRepository, Mockito.times(0)).delete(product);
    }

}