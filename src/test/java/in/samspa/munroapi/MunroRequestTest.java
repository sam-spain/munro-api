package in.samspa.munroapi;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MunroRequestTest {


    @Test
    void maxResultsCannotBeBelowOne() {
        Exception exception = assertThrows(BadApiQueryException.class,
                () -> new MunroRequest.Builder().withMaxResults(0).build());
        assertEquals("Max results should be a number above 0", exception.getMessage());
    }

    @Test
    void maxResultsCannotBeAboveALargeValue() {
        Exception exception = assertThrows(BadApiQueryException.class,
                () -> new MunroRequest.Builder().withMaxResults(99999).build());
        assertEquals("Max results cannot be above 9999", exception.getMessage());
    }

    @Test
    void sortsShouldBeMultipleOfTwo() {
        Exception exception = assertThrows(BadApiQueryException.class,
                () -> new MunroRequest.Builder().withSorting(Arrays.asList("height", "asc", "name")).build());
        assertEquals("Sorting should follow format of [field, asc/desc]. e.g. 'height', 'asc'", exception.getMessage());
    }

    @Test
    void sortShouldMatchColumns() {
        Exception exception = assertThrows(BadApiQueryException.class,
                () -> new MunroRequest.Builder().withSorting(Arrays.asList("nothing", "asc")).build());
        assertEquals("Can only sort fields: 'name', 'height', 'category', 'grid reference'", exception.getMessage());
    }

    @Test
    void minHeightCannotBeBelowZero() {
        Exception exception = assertThrows(BadApiQueryException.class,
                () -> new MunroRequest.Builder().withMinHeight(-1D).build());
        assertEquals("Minimum height cannot be below one", exception.getMessage());
    }

    @Test
    void maxHeightCannotBeAboveVeryLargeValue() {
        Exception exception = assertThrows(BadApiQueryException.class,
                () -> new MunroRequest.Builder().withMaxHeight(99999999D).build());
        assertEquals("Maximum height cannot be above 9999999", exception.getMessage());
    }

    @Test
    void maxHeightCannotBeBelowMinHeight() {
        Exception exception = assertThrows(BadApiQueryException.class,
                () -> new MunroRequest.Builder().withMinHeight(10D).withMaxHeight(5D).build());
        assertEquals("Maximum height cannot be below minimum height", exception.getMessage());
    }

    @Test
    void categoryFilterMustMatchCategories() {
        Exception exception = assertThrows(BadApiQueryException.class,
                () -> new MunroRequest.Builder().withCategoryFilter("nothing").build());
        assertEquals("Category filter must be 'MUN' or 'TOP'", exception.getMessage());
    }
}