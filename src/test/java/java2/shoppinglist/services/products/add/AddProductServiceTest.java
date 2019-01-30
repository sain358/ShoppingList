package java2.shoppinglist.services.products.add;

import java2.shoppinglist.domains.Product;
import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.repositories.ProductRepository;
import java2.shoppinglist.services.ShoppingListError;
import java2.shoppinglist.services.products.add.validation.AddProductValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AddProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private AddProductValidator validator;

    @InjectMocks
    private AddProductService service = new AddProductService();

    private AddProductRequest request;
    private Product product;
    private List<ShoppingListError> errors = new ArrayList<>();

    @Before
    public void init() {
        request = new AddProductRequest(
                "title", "description", 1, new ShoppingList());
        product = new Product();
        product.setTitle(request.getProductTitle());
        product.setDescription(request.getProductDescription());
        product.setQuantity(request.getQuantity());
        product.setShoppingList(request.getShoppingList());
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
        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

    @Test
    public void shouldNotCallRepositorySaveMethod() {
        errors.add(new ShoppingListError("", ""));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        service.execute(request);
        Mockito.verify(productRepository, Mockito.times(0)).save(product);
    }

}