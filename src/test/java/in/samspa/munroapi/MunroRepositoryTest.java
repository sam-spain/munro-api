package in.samspa.munroapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MunroRepositoryTest {

    public static final String MUNRO_NAME = "Ben Chonzie";
    public static final Double MUNRO_HEIGHT = 931D;
    public static final String MUNRO_CATEGORY = "MUN";
    public static final String MUNRO_GRID_REFERENCE = "NN773308";

    private MunroRepository munroRepository;
    private CsvToMunroBuilder mockedBuilder = Mockito.mock(CsvToMunroBuilder.class);
    private String fileLocation = "src/main/resources/static/munrotab_v6.2.csv";

    @BeforeEach
    void init() {
        munroRepository = new MunroRepository(mockedBuilder);
    }

    @Test
    void findFirstMunro() {
        Munro expectedMunro = new Munro(MUNRO_NAME, MUNRO_HEIGHT, MUNRO_CATEGORY, MUNRO_GRID_REFERENCE);
        when(mockedBuilder.convertCsvToMunro(fileLocation)).
                thenReturn(Collections.singletonList(expectedMunro));
        List<Munro> foundMunro = munroRepository.find();
        assertNotEquals(0, foundMunro.size());
        Munro firstMunro = foundMunro.get(0);
        assertEquals(expectedMunro.getName(), firstMunro.getName());
        assertEquals(expectedMunro.getHeight(), firstMunro.getHeight());
        assertEquals(expectedMunro.getCategory(), firstMunro.getCategory());
        assertEquals(expectedMunro.getGridReference(), firstMunro.getGridReference());
    }

    @Test
    void filterAnyInvalidMunroData() {
        Munro expectedMunro = new Munro("", null, "", "");
        when(mockedBuilder.convertCsvToMunro(fileLocation)).
                thenReturn(Collections.singletonList(expectedMunro));
        List<Munro> foundMunro = munroRepository.find();
        assertEquals(0, foundMunro.size());
    }

}