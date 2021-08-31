package in.samspa.munroapi;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MunroService {


    private MunroRepository munroRepository;

    public MunroService(MunroRepository munroRepository) {
        this.munroRepository = munroRepository;
    }

    List<Munro> findData(MunroRequest munroRequest) {
       List<Munro> munroData = munroRepository.find();
       return munroData.stream().filter(munro -> munro.getHeight() <= munroRequest.getMaxHeight()).collect(Collectors.toList());
    }
}
