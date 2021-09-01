package in.samspa.munroapi;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MunroService {


    private MunroRepository munroRepository;

    public MunroService(MunroRepository munroRepository) {
        this.munroRepository = munroRepository;
    }

    List<Munro> findData(MunroRequest munroRequest) {
       List<Munro> munroData = munroRepository.find();
        Stream<Munro> munroStream = munroData.stream().filter(munro -> munro.getHeight() <= munroRequest.getMaxHeight())
                .filter(munro -> munro.getHeight() >= munroRequest.getMinHeight());
        if(!munroRequest.getCategoryFilter().equals(MunroCategoryFilter.ALL)) {
            munroStream = munroStream.filter(munro -> munro.getCategory().equals(munroRequest.getCategoryFilter().name()));
        }
        return munroStream.collect(Collectors.toList());
    }
}
