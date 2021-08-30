package in.samspa.munroapi;

public class MunroSorts {


    private MunroSortingFields fieldToSort;
    private boolean isAscending;

    public MunroSorts(MunroSortingFields fieldToSort, boolean isAscending) {
        this.fieldToSort = fieldToSort;
        this.isAscending = isAscending;
    }
}
