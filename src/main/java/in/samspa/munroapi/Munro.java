package in.samspa.munroapi;

import com.opencsv.bean.CsvBindByName;

public class Munro {

    public Munro() {
    }

    Munro(String name, Double height, String category, String gridReference) {
        this.name = name;
        this.height = height;
        this.category = category;
        this.gridReference = gridReference;
    }

    @CsvBindByName(column = "Name")
    private String name;

    @CsvBindByName(column = "Height (m)")
    private Double height;

    @CsvBindByName(column = "Post 1997")
    private String category;

    @CsvBindByName(column = "Grid Ref")
    private String gridReference;

    public String getName() {
        return name;
    }

    public Double getHeight() {
        return height;
    }

    public String getCategory() {
        return category;
    }

    public String getGridReference() {
        return gridReference;
    }

    boolean allValuesExist() {
        return height != null && !name.isEmpty() && !category.isEmpty() && !gridReference.isEmpty();
    }
}
