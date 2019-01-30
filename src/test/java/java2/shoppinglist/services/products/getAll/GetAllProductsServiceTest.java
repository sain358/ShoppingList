package java2.shoppinglist.services.products.getAll;

import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.repositories.ProductRepository;
import java2.shoppinglist.services.ShoppingListError;
import java2.shoppinglist.services.products.getAll.validation.GetAllProductsValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetAllProductsServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private GetAllProductsValidator validator;

    @InjectMocks
    private GetAllProductsService service = new GetAllProductsService();

    private ShoppingList shoppingList= new ShoppingList();
    private GetAllProductsRequest request =  new GetAllProductsRequest(shoppingList);

    @Test
    public void shouldCallRepositoryMethodGetAllByShoppingList() {
        service.execute(request);
        Mockito.verify(productRepository, Mockito.times(1)).
                getAllByShoppingList(shoppingList);
    }

    @Test
    public void shouldCallValidatorMethodValidate() {
        service.execute(request);
        Mockito.verify(validator, Mockito.times(1)).
                validate(request);
    }

}