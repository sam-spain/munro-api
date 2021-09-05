package in.samspa.munroapi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MunroRequestTest {


    @Test
    void maxResultsCannotBeBelowOne() {
        Exception exception = assertThrows(BadApiQueryException.class,
                () -> new MunroRequest.Builder().withMaxResults(0).build());
        assertEquals("Max results should be a number above 0", exception.getMessage());
    }
}