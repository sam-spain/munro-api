package in.samspa.munroapi;

import java.util.Arrays;
import java.util.Optional;

public enum MunroCategoryFilter {
    MUNRO("munro"),
    TOP("top"),
    ALL("all");

    private String getParam;

    MunroCategoryFilter(String getParam) {this.getParam = getParam;}

    public static Optional<MunroCategoryFilter> fromString(String getParam) {
        return Arrays.stream(MunroCategoryFilter.values()).filter(category -> category.getParam.equals(getParam)).findFirst();
    }
}
