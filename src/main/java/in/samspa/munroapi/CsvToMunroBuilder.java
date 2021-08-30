package in.samspa.munroapi;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

class CsvToMunroBuilder {

    List<Munro> convertCsvToMunro(String path) {
        Path dataPath = Paths.get(path);

        try(BufferedReader br = Files.newBufferedReader(dataPath, StandardCharsets.ISO_8859_1)) {
            HeaderColumnNameMappingStrategy<Munro> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(Munro.class);

            CsvToBean<Munro> csvToBean = new CsvToBeanBuilder<Munro>(br)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        } catch (RuntimeException | IOException e) {
            return Collections.emptyList();
        }
    }

}
