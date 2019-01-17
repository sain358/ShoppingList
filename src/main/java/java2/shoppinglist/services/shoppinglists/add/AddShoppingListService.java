package java2.shoppinglist.services.shoppinglists.add;

import java2.shoppinglist.database.ShoppingListRepository;
import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.services.ShoppingListError;
import java2.shoppinglist.services.shoppinglists.add.AddShoppingListRequest;
import java2.shoppinglist.services.shoppinglists.add.AddShoppingListResponse;
import java2.shoppinglist.services.shoppinglists.add.AddShoppingListValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class AddShoppingListService {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private AddShoppingListValidator validator;

    public AddShoppingListResponse execute (AddShoppingListRequest request){

        AddShoppingListResponse response = new AddShoppingListResponse();

        List<ShoppingListError> shoppingListErrors = validator.validate(request);
        response.setShoppingListErrors(shoppingListErrors);
        if (!shoppingListErrors.isEmpty()) {
            return response;
        }

        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setUser(request.getUser());
        shoppingList.setTitle(request.getTitle());
        shoppingListRepository.save(shoppingList);

        response.setShoppingList(shoppingList);
        return response;
    }
}
