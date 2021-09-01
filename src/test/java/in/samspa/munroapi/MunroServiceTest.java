package in.samspa.munroapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MunroServiceTest {


    private MunroRepository mockedRepository = Mockito.mock(MunroRepository.class);
    private MunroService munroService;


    @BeforeEach
    void init() {
        munroService = new MunroService(mockedRepository);
    }

    @Test
    void getDataWithDefaultQueries() {
        MunroRequest munroRequest = new MunroRequest.Builder().build();
        Munro munro = new Munro("Sam", 45.5D, "MUN", "001234");
        when(mockedRepository.find()).thenReturn(List.of(munro));
        List<Munro> returnedMunro = munroService.findData(munroRequest);
        assertEquals(1, returnedMunro.size());
    }

    @Test
    void filterDataByMaxHeight() {
        MunroRequest munroRequest = new MunroRequest.Builder().withMaxHeight(60.5D).build();
        List<Munro> munros = makeMunros();
        when(mockedRepository.find()).thenReturn(munros);
        List<Munro> returnedMunro = munroService.findData(munroRequest);
        assertEquals(3, returnedMunro.size());
    }

    @Test
    void filterDataByMinHeight() {
        MunroRequest minHeightRequest = new MunroRequest.Builder().withMinHeight(45.6D).build();
        List<Munro> munros = makeMunros();
        when(mockedRepository.find()).thenReturn(munros);
        List<Munro> returnedMunro = munroService.findData(minHeightRequest);
        assertEquals(3, returnedMunro.size());
    }

    private List<Munro> makeMunros() {
        Munro munroToInclude = new Munro("Sam", 45.5D, "MUN", "001234");
        Munro munroTOFilter = new Munro("Bill", 60.5D, "MUN", "001234");
        Munro otherMunroToFilter = new Munro("Charlie", 45.6D, "MUN", "001234");
        Munro largestMunro = new Munro("Dillinger", 60.6D, "MUN", "001234");
        return Arrays.asList(munroToInclude, munroTOFilter, otherMunroToFilter, largestMunro);
    }

}
