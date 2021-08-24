package in.samspa.munroapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
class MunroRepositoryTest {

    private MunroRepository munroRepository;

    @BeforeEach
    void init() {
        munroRepository = new MunroRepository();
    }

    @Test
    void findFirstMunro() {
        List<Munro> foundMunro = munroRepository.find();
        assertNotEquals(0, foundMunro.size());
        Munro firstMunro = foundMunro.get(0);
        assertEquals("Ben Chonzie", firstMunro.getName());
        assertEquals(931, firstMunro.getHeight());
        assertEquals("MUN", firstMunro.getCategory());
        assertEquals("NN773308", firstMunro.getGridReference());
    }
}