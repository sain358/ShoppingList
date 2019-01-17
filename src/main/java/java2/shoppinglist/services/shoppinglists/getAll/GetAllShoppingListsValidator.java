package java2.shoppinglist.services.shoppinglists.getAll;

import java2.shoppinglist.database.ShoppingListRepository;
import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.domains.User;
import java2.shoppinglist.services.ShoppingListError;
import java2.shoppinglist.services.shoppinglists.getAll.GetAllShoppingListsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetAllShoppingListsValidator {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    public List<ShoppingListError> validate(GetAllShoppingListsRequest request) {
        List<ShoppingListError> errors = new ArrayList<>();
        validateEmptyShoppingList(request.getUser()).ifPresent(errors::add);
        return errors;
    }

    public Optional<ShoppingListError> validateEmptyShoppingList(User user) {
        List<ShoppingList> shoppingLists = shoppingListRepository.findShoppingLists(user);
        if (shoppingLists.isEmpty()) {
            ShoppingListError shoppingListError = new ShoppingListError("", "There are no Shopping Lists!");
            return Optional.of(shoppingListError);
        }
        return Optional.empty();
    }

}
