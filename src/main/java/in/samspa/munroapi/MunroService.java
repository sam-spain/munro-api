package in.samspa.munroapi;

import org.springframework.stereotype.Service;

import java.util.Comparator;
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
        munroStream = sort(munroRequest, munroStream);
        return munroStream.limit(munroRequest.getMaxResults()).collect(Collectors.toList());
    }

    private Stream<Munro> sort(MunroRequest munroRequest, Stream<Munro> munroStream) {
        Comparator<Munro> munroComparator = Comparator.comparing(Munro::getGridReference);
        for (int i = 0; i < munroRequest.getMunroSorts().size() ; i++ ) {

            switch (munroRequest.getMunroSorts().get(i).getFieldToSort()) {
                case NAME:
                    munroComparator = munroComparator.thenComparing(Munro::getName);
                    break;
                case HEIGHT:
                    munroComparator = munroComparator.thenComparing(Munro::getHeight);
                    break;
                case CATEGORY:
                    munroComparator = munroComparator.thenComparing(Munro::getCategory);
                    break;
                case GRID_REFERENCE:
                    munroComparator = munroComparator.thenComparing(Munro::getGridReference);
                    break;
                default:
                    throw new RuntimeException();
            }
            if(!munroRequest.getMunroSorts().get(i).isAscending()) {
                munroComparator = munroComparator.reversed();
            }
        }

        return munroStream.sorted(munroComparator);
    }
}
