package java2.shoppinglist.services.shoppinglists.get;

import java2.shoppinglist.database.ShoppingListRepository;
import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.domains.User;
import java2.shoppinglist.services.ShoppingListError;
import java2.shoppinglist.services.shoppinglists.get.GetShoppingListRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class GetShoppingListValidator {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    public List<ShoppingListError> validate(GetShoppingListRequest request) {
        List<ShoppingListError> errors = new ArrayList<>();
        validateShoppingListExistence(request.getUser(), request.getTitle()).ifPresent(errors::add);
        return errors;
    }

    public Optional<ShoppingListError> validateShoppingListExistence(User user, String title) {
        Optional<ShoppingList> shoppingListOptional = shoppingListRepository.findByUserAndTitle(user, title);
        if (!shoppingListOptional.isPresent()) {
            ShoppingListError shoppingListError = new ShoppingListError("title", "No such Shopping List found!");
            return Optional.of(shoppingListError);
        }
        return Optional.empty();

    }

}