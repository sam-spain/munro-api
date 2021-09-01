package in.samspa.munroapi;

import java.util.Arrays;
import java.util.Optional;

public enum MunroSortingFields {

    NAME("name"),
    HEIGHT("height"),
    CATEGORY("category"),
    GRID_REFERENCE("grid_reference");

    String getParam;

    MunroSortingFields(String getParam) {
        this.getParam = getParam;
    }

    public static Optional<MunroSortingFields> fromString(String getParam) {
        return Arrays.stream(MunroSortingFields.values()).filter(field -> field.getParam.equals(getParam)).findFirst();
    }
}
