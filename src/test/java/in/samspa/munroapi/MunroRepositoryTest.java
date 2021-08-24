package in.samspa.munroapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MunroRepositoryTest {

    @BeforeEach
    void init() {
        munroRepository = new MunroRepository();
    }

    MunroRepository munroRepository;

    @Test
    void contextLoads() {
        munroRepository.find();
    }
}