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

        Builder withMaxHeight(Double maxHeight) {
            this.maxHeight = maxHeight;
            return this;
        }

        Builder withMinHeight(Double minHeight) {
            this.minHeight = minHeight;
            return this;
        }

        Builder withCategoryFilter(String categoryFilter) {
            this.categoryFilter = MunroCategoryFilter.fromString(categoryFilter).get();
            return this;
        }

        Builder withSorting(List<String> sorts) {
            if(sorts == null) {
                return this;
            }
            List<MunroSorts> newMunroSorts = new ArrayList<>();
            for (int i = 0; i < sorts.size(); i = i + 2) {
                MunroSortingFields fieldToSort = MunroSortingFields.fromString(sorts.get(i)).get();
                boolean isAscending = sorts.get(i + 1).equals("asc");
                newMunroSorts.add(new MunroSorts(fieldToSort, isAscending));
            }
            this.munroSorts = newMunroSorts;
            return this;
        }

        Builder withMaxResults(Integer maxResults) {
            if(maxResults <= 0) {
                throw new BadApiQueryException("Max results should be a number above 0");
            }
            this.maxResults = maxResults;
            return this;
        }

        public MunroRequest build() {
            return new MunroRequest(this);
        }
    }
}
