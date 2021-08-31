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
        MunroRequest munroRequest = new MunroRequest.Builder().withMaxHeight(45.5D).build();
        Munro munroToInclude = new Munro("Sam", 45.5D, "MUN", "001234");
        Munro munroTOFilter = new Munro("Bill", 60.5D, "MUN", "001234");
        Munro otherMunroToFilter = new Munro("Charlie", 45.6D, "MUN", "001234");
        when(mockedRepository.find()).thenReturn(Arrays.asList(munroToInclude, munroTOFilter, otherMunroToFilter));
        List<Munro> returnedMunro = munroService.findData(munroRequest);
        assertEquals(1, returnedMunro.size());
    }

}
