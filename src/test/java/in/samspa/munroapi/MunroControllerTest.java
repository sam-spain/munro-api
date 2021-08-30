package in.samspa.munroapi;


import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.hamcrest.Matchers.*;

import java.util.Arrays;

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
        when(munroService.findData()).thenReturn(Arrays.asList(firstMunro, secondMunro));

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

}