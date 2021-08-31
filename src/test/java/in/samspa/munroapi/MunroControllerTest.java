package in.samspa.munroapi;


import static in.samspa.munroapi.MunroSortingFields.CATEGORY;
import static in.samspa.munroapi.MunroSortingFields.HEIGHT;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MunroController.class)
class MunroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MunroService munroService;

    @Test
    void getData() throws Exception {
        Munro firstMunro = new Munro("Sam", 2.5D, "MUN", "000112");
        Munro secondMunro = new Munro("Sofia", 3.5D, "TOP", "223110");
        when(munroService.findData(ArgumentMatchers.any())).thenReturn(Arrays.asList(firstMunro, secondMunro));

        mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(firstMunro.getName())))
                .andExpect(jsonPath("$[0].height", is(firstMunro.getHeight())))
                .andExpect(jsonPath("$[0].category", is(firstMunro.getCategory())))
                .andExpect(jsonPath("$[0].gridReference", is(firstMunro.getGridReference())))
                .andExpect(jsonPath("$[1].name", is(secondMunro.getName())))
                .andExpect(jsonPath("$[1].height", is(secondMunro.getHeight())))
                .andExpect(jsonPath("$[1].category", is(secondMunro.getCategory())))
                .andExpect(jsonPath("$[1].gridReference", is(secondMunro.getGridReference())));
    }

    @Test
    void noQueryArgsCreatesDefaultQuery() throws Exception {
        when(munroService.findData(ArgumentMatchers.any())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
        ArgumentCaptor<MunroRequest> munroRequestCaptor = ArgumentCaptor.forClass(MunroRequest.class);
        verify(munroService).findData(munroRequestCaptor.capture());
        MunroRequest sentRequest = munroRequestCaptor.getValue();
        assertEquals(999999.0D, sentRequest.getMaxHeight());
        assertEquals(0, sentRequest.getMinHeight());
        assertEquals(MunroCategoryFilter.ALL, sentRequest.getCategoryFilter());
        assertEquals(9999, sentRequest.getMaxResults());
        assertEquals(Collections.emptyList(), sentRequest.getMunroSorts());
    }

    @Test
    void queryWithMaxHeight() throws Exception {
        when(munroService.findData(ArgumentMatchers.any())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/").param("maxHeight", "25.5")).andDo(print()).andExpect(status().isOk());

        ArgumentCaptor<MunroRequest> munroRequestCaptor = ArgumentCaptor.forClass(MunroRequest.class);
        verify(munroService).findData(munroRequestCaptor.capture());
        MunroRequest sentRequest = munroRequestCaptor.getValue();
        assertEquals(25.5D, sentRequest.getMaxHeight());
    }

    @Test
    void queryWithMinHeight() throws Exception {
        when(munroService.findData(ArgumentMatchers.any())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/").param("minHeight", "10.4")).andDo(print()).andExpect(status().isOk());

        ArgumentCaptor<MunroRequest> munroRequestCaptor = ArgumentCaptor.forClass(MunroRequest.class);
        verify(munroService).findData(munroRequestCaptor.capture());
        MunroRequest sentRequest = munroRequestCaptor.getValue();
        assertEquals(10.4D, sentRequest.getMinHeight());
    }

    @Test
    void queryWithTopFilter() throws Exception {
        when(munroService.findData(ArgumentMatchers.any())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/").param("categoryFilter", "top")).andDo(print()).andExpect(status().isOk());

        ArgumentCaptor<MunroRequest> munroRequestCaptor = ArgumentCaptor.forClass(MunroRequest.class);
        verify(munroService).findData(munroRequestCaptor.capture());
        MunroRequest sentRquest = munroRequestCaptor.getValue();
        assertEquals(MunroCategoryFilter.TOP, sentRquest.getCategoryFilter());
    }

    @Test
    void queryWithSorts() throws Exception {
        when(munroService.findData(ArgumentMatchers.any())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/").param("sort", "height", "asc", "category", "desc"));

        ArgumentCaptor<MunroRequest> munroRequestCaptor = ArgumentCaptor.forClass(MunroRequest.class);
        verify(munroService).findData(munroRequestCaptor.capture());
        MunroRequest sentRequest = munroRequestCaptor.getValue();
        assertEquals(HEIGHT, sentRequest.getMunroSorts().get(0).getFieldToSort());
        assertTrue(sentRequest.getMunroSorts().get(0).isAscending());
        assertEquals(CATEGORY, sentRequest.getMunroSorts().get(1).getFieldToSort());
        assertFalse(sentRequest.getMunroSorts().get(1).isAscending());
    }

    @Test
    void queryWithMaxResults() throws Exception {
        when(munroService.findData(ArgumentMatchers.any())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/").param("maxResults", "10"));

        ArgumentCaptor<MunroRequest> munroRequestCaptor = ArgumentCaptor.forClass(MunroRequest.class);
        verify(munroService).findData(munroRequestCaptor.capture());
        MunroRequest sentRequest = munroRequestCaptor.getValue();
        assertEquals(10, sentRequest.getMaxResults());
    }

}