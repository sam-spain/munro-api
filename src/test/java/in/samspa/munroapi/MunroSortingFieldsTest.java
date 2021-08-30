package in.samspa.munroapi;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MunroSortingFieldsTest {

    @Test
    void returnEmptyIfNoMatchingFieldFound() {
        Optional<MunroSortingFields> field = MunroSortingFields.fromString("not_a_value");
        assertTrue(field.isEmpty());
    }

    @Test
    void returnAnyMatchingMunroField() {
        Optional<MunroSortingFields> field = MunroSortingFields.fromString("name");
        assertTrue(field.isPresent());
        assertEquals(field.get(), MunroSortingFields.NAME);
    }

}