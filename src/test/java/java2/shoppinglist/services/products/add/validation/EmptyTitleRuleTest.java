package java2.shoppinglist.services.products.add.validation;

import java2.shoppinglist.services.ShoppingListError;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class EmptyTitleRuleTest {

    private EmptyTitleRule rule = new EmptyTitleRule();

    @Test
    public void shouldNotReturnErrorIfTitleNotEmpty() {
        Optional<ShoppingListError> error = rule.execute("title");
        assertFalse(error.isPresent());
    }

    @Test
    public void shouldReturnErrorIfTitleIsEmpty() {
        Optional<ShoppingListError> error = rule.execute("   ");
        assertTrue(error.isPresent());
        assertEquals("title", error.get().getPointer());
        assertEquals("Empty title not allowed!", error.get().getDescription());
    }

}