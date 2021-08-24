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
    void getMunroWithName() {
        List<Munro> foundMunro = munroRepository.find();
        assertNotEquals(0, foundMunro.size());
        assertEquals("Ben Chonzie", foundMunro.get(0).getName());
    }
}