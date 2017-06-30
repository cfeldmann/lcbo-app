package lcbo.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

/**
 * Java object representation of the JSON search results returned from lcboapi.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult {

    private Long status;

    // This variable is (horribly) named result to take advantage of automatic assignment from the returned json
    // but it is actually a list of stores.
    // There must be a way to specify a mapping to a better variable name here; I'll revisit it if time permits.
    // For now, call it "result" internally but provide a getStores() method instead of getResult() so that calling
    // classes can refer to it properly.
    private Store[] result;

    public SearchResult() {
    }

    public Long getStatus() {
        return this.status;
    }

    // Expose the stores as stores!
    public Store[] getStores() {
        return this.result;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public void setResult(Store[] result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "status=" + status +
                ", result=" + Arrays.toString(result) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchResult that = (SearchResult) o;

        if (!status.equals(that.status)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        int result1 = status.hashCode();
        result1 = 31 * result1 + Arrays.hashCode(result);
        return result1;
    }
}