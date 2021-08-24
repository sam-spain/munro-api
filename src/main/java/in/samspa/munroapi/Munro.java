package in.samspa.munroapi;

import com.opencsv.bean.CsvBindByName;

public class Munro {

    @CsvBindByName(column = "Name")
    private String name;

    String getName() {
        return name;
    }
}
