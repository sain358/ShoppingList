package java2.shoppinglist.services.products.add.validation;

import java2.shoppinglist.services.ShoppingListError;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmptyTitleRule {

    public Optional<ShoppingListError> execute(String title) {
        Pattern pattern = Pattern.compile("^\\s*$");
        Matcher matcher = pattern.matcher(title);
        if (matcher.find()) {
            ShoppingListError shoppingListError = new ShoppingListError("title", "Empty title not allowed!");
            return Optional.of(shoppingListError);
        }
        return Optional.empty();
    }
}
