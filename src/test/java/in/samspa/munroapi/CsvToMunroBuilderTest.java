package in.samspa.munroapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

class CsvToMunroBuilderTest {

    CsvToMunroBuilder csvToMunroBuilder;

    @BeforeEach
    void init() {
        csvToMunroBuilder = new CsvToMunroBuilder();
    }

    @Test
    void returnEmptyListWhenNoFileExists() {
        assertEquals(emptyList(), csvToMunroBuilder.convertCsvToMunro("none.csv") );
    }

    @Test
    void returnEmptyListWhenFileHasNoValidRows() {
        assertEquals(emptyList(), csvToMunroBuilder.convertCsvToMunro("src/test/java/resources/invalid-row.csv"));
    }

    @Test
    void returnRowsWithNoData() {
        assertEquals(1, csvToMunroBuilder.convertCsvToMunro("src/test/java/resources/no-rows.csv").size());
    }

    @Test
    void returnOneValidRow() {
        assertEquals(1, csvToMunroBuilder.convertCsvToMunro("src/test/java/resources/one-row.csv").size());
    }

    @Test
    void returnMultipleRows() {
        assertEquals(2, csvToMunroBuilder.convertCsvToMunro("src/test/java/resources/two-rows.csv").size());
    }

}