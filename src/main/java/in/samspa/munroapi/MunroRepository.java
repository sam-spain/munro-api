package in.samspa.munroapi;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@Component
class MunroRepository {

    private String fileLocation = "src/main/resources/static/munrotab_v6.2.csv";

    private CsvToMunroBuilder csvToMunroBuilder;

    public MunroRepository(CsvToMunroBuilder csvToMunroBuilder) {
        this.csvToMunroBuilder = csvToMunroBuilder;
    }

    List<Munro> find() {
        return csvToMunroBuilder.convertCsvToMunro(fileLocation);
    }
}
