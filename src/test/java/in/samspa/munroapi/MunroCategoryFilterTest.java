package in.samspa.munroapi;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MunroCategoryFilterTest {

    @Test
    void returnEmptyIfNoMatchingCategoryFound() {
        Optional<MunroCategoryFilter> category = MunroCategoryFilter.fromString("not_a_category");
        assertTrue(category.isEmpty());
    }

    @Test
    void returnAnyMatchingCategory() {
        Optional<MunroCategoryFilter> category = MunroCategoryFilter.fromString("top");
        assertTrue(category.isPresent());
        assertEquals(MunroCategoryFilter.TOP, category.get());
    }

    @Test
    void emptyStringRecognisedAsAll() {
        Optional<MunroCategoryFilter> category = MunroCategoryFilter.fromString("");
        assertTrue(category.isPresent());
        assertEquals(MunroCategoryFilter.ALL, category.get());
    }
}