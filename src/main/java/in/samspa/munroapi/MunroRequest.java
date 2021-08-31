package in.samspa.munroapi;

import java.util.ArrayList;
import java.util.List;

public class MunroRequest {

    private List<MunroSorts> munroSorts;
    private int maxResults;
    private double maxHeight;
    private double minHeight;
    private MunroCategoryFilter categoryFilter;

    private MunroRequest(Builder builder) {
        this.munroSorts = builder.munroSorts;
        this.maxResults = builder.maxResults;
        this.maxHeight = builder.maxHeight;
        this.minHeight = builder.minHeight;
        this.categoryFilter = builder.categoryFilter;
    }

    public List<MunroSorts> getMunroSorts() {
        return munroSorts;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public double getMaxHeight() {
        return maxHeight;
    }

    public double getMinHeight() {
        return minHeight;
    }

    public MunroCategoryFilter getCategoryFilter() {
        return categoryFilter;
    }

    static class Builder {
        private List<MunroSorts> munroSorts = new ArrayList<>();
        private int maxResults = Integer.MAX_VALUE;
        private double maxHeight = Integer.MAX_VALUE;
        private double minHeight = 0;
        private MunroCategoryFilter categoryFilter = MunroCategoryFilter.ALL;

        public MunroRequest build() {
            return new MunroRequest(this);
        }
    }
}
