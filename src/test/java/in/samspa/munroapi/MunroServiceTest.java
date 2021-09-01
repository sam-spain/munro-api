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

    @Test
    void filterDataByCategoryTop() {
        MunroRequest topFilter = new MunroRequest.Builder().withCategoryFilter(MunroCategoryFilter.TOP.getParam).build();
        List<Munro> munros = makeMunros();
        when(mockedRepository.find()).thenReturn(munros);
        List<Munro> returnedMunro = munroService.findData(topFilter);
        assertEquals(1, returnedMunro.size());
    }

    @Test
    void limitResults() {
        MunroRequest maxResultsRequest = new MunroRequest.Builder().withMaxResults(1).build();
        List<Munro> munros = makeMunros();
        when(mockedRepository.find()).thenReturn(munros);
        List<Munro> returnedMunro = munroService.findData(maxResultsRequest);
        assertEquals(1, returnedMunro.size());
    }

    @Test
    void sortResults() {
        MunroRequest sortRequest = new MunroRequest.Builder().withSorting(Arrays.asList(MunroSortingFields.CATEGORY.getParam, "desc", MunroSortingFields.HEIGHT.getParam, "asc")).build();
        List<Munro> munros = makeMunros();
        when(mockedRepository.find()).thenReturn(munros);
        List<Munro> sortedMunro = munroService.findData(sortRequest);
        assertEquals("TOP", sortedMunro.get(0).getCategory());
        assertEquals(45.5D, sortedMunro.get(1).getHeight());
        assertEquals(45.6D, sortedMunro.get(2).getHeight());
        assertEquals(60.6D, sortedMunro.get(3).getHeight());
    }

    private List<Munro> makeMunros() {
        Munro otherMunroToFilter = new Munro("Charlie", 45.6D, "MUN", "001233");
        Munro munroToInclude = new Munro("Sam", 45.5D, "MUN", "001234");
        Munro munroTOFilter = new Munro("Bill", 60.5D, "TOP", "001235");
        Munro largestMunro = new Munro("Dillinger", 60.6D, "MUN", "001232");
        return Arrays.asList(munroToInclude, munroTOFilter, otherMunroToFilter, largestMunro);
    }

}
