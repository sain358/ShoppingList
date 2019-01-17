package java2.shoppinglist.services.shoppinglists.add;

import java2.shoppinglist.database.ShoppingListRepository;
import java2.shoppinglist.domains.ShoppingList;
import java2.shoppinglist.domains.User;
import java2.shoppinglist.services.ShoppingListError;
import java2.shoppinglist.services.shoppinglists.add.AddShoppingListRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AddShoppingListValidator {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    public List<ShoppingListError> validate(AddShoppingListRequest request) {
        List<ShoppingListError> errors = new ArrayList<>();
        validateDuplicateTitle(request.getUser(), request.getTitle()).ifPresent(errors::add);
        validateEmptyTitle(request.getTitle()).ifPresent(errors::add);
        return errors;
    }

    private Optional<ShoppingListError> validateDuplicateTitle (User user, String title){
        Optional<ShoppingList> shoppingListOptional = shoppingListRepository.findByUserAndTitle(user, title);
        if (shoppingListOptional.isPresent()){
            return Optional.of(new ShoppingListError("title", "This Shopping List already exists!"));
        }
        return Optional.empty();
    }

    private Optional<ShoppingListError> validateEmptyTitle(String title) {
        Pattern pattern = Pattern.compile("^\\s*$");
        Matcher matcher = pattern.matcher(title);
        if (matcher.find()) {
            return Optional.of(new ShoppingListError("title", "Empty title not allowed!"));
        }
        return Optional.empty();
    }


}
