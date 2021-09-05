package in.samspa.munroapi;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
            if(maxHeight > 9999999) {
                throw new BadApiQueryException("Maximum height cannot be above 9999999");
            }
            this.maxHeight = maxHeight;
            return this;
        }

        Builder withMinHeight(Double minHeight) {
            if(minHeight < 1) {
                throw new BadApiQueryException("Minimum height cannot be below one");
            }
            this.minHeight = minHeight;
            return this;
        }

        Builder withCategoryFilter(String categoryFilter) {
            try{
                this.categoryFilter = MunroCategoryFilter.fromString(categoryFilter).get();
            }catch (NoSuchElementException e) {
                throw new BadApiQueryException("Category filter must be 'MUN' or 'TOP'");
            }
            return this;
        }

        Builder withSorting(List<String> sorts) {
            if(sorts == null) {
                return this;
            }
            if(sorts.size() % 2 != 0) {
                throw new BadApiQueryException("Sorting should follow format of [field, asc/desc]. e.g. 'height', 'asc'");
            }
            List<MunroSorts> newMunroSorts = new ArrayList<>();
            for (int i = 0; i < sorts.size(); i = i + 2) {
                MunroSortingFields fieldToSort;
                try {
                    fieldToSort = MunroSortingFields.fromString(sorts.get(i)).get();
                }catch (NoSuchElementException e) {
                    throw new BadApiQueryException("Can only sort fields: 'name', 'height', 'category', 'grid reference'");
                }
                boolean isAscending = sorts.get(i + 1).equals("asc");
                newMunroSorts.add(new MunroSorts(fieldToSort, isAscending));
            }
            this.munroSorts = newMunroSorts;
            return this;
        }

        Builder withMaxResults(Integer maxResults) {
            if(maxResults <= 0) {
                throw new BadApiQueryException("Max results should be a number above 0");
            } else if (maxResults > 9999) {
                throw new BadApiQueryException("Max results cannot be above 9999");
            }
            this.maxResults = maxResults;
            return this;
        }

        public MunroRequest build() {
            if(maxHeight < minHeight) {
                throw new BadApiQueryException("Maximum height cannot be below minimum height");
            }
            return new MunroRequest(this);
        }
    }
}
