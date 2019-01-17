package java2.shoppinglist.services.shoppinglists.remove;

import java2.shoppinglist.database.ShoppingListRepository;
import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.services.ShoppingListError;
import java2.shoppinglist.services.shoppinglists.remove.RemoveShoppingListRequest;
import java2.shoppinglist.services.shoppinglists.remove.RemoveShoppingListResponse;
import java2.shoppinglist.services.shoppinglists.remove.RemoveShoppingListValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class RemoveShoppingListService {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private RemoveShoppingListValidator validator;

    public RemoveShoppingListResponse execute(RemoveShoppingListRequest request) {

        List<ShoppingListError> shoppingListErrors = validator.validate(request);
        if (!shoppingListErrors.isEmpty()) {
            return new RemoveShoppingListResponse(shoppingListErrors);
        }

        Optional<ShoppingList> shoppingListOptional =
                shoppingListRepository.findByUserAndTitle(request.getUser(), request.getTitle());
        shoppingListRepository.remove(shoppingListOptional.get());
        return new RemoveShoppingListResponse(shoppingListErrors);

    }

}
