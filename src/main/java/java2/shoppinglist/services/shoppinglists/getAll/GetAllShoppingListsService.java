package java2.shoppinglist.services.shoppinglists.getAll;

import java2.shoppinglist.repositories.ShoppingListRepository;
import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.services.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetAllShoppingListsService {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private GetAllShoppingListsValidator validator;

    public GetAllShoppingListsResponse execute(GetAllShoppingListsRequest request) {

        List<ShoppingList> shoppingLists = shoppingListRepository.findAllByUser(request.getUser());
        List<ShoppingListError> shoppingListErrors = validator.validate(request);
        return new GetAllShoppingListsResponse(shoppingLists, shoppingListErrors);
    }

}
