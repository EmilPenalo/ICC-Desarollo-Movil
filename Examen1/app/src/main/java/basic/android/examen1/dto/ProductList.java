package basic.android.examen1.dto;

import basic.android.examen1.entity.Product;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductList {
    @SerializedName("products")
    public List<Product> products;

    @SerializedName("total")
    public int total;
    @SerializedName("skip")
    public int skip;

    @SerializedName("limit")
    public int limit;

    public ProductList(List<Product> products, int total, int skip, int limit) {
        this.products = products;
        this.total = total;
        this.skip = skip;
        this.limit = limit;
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getTotal() {
        return total;
    }

    public int getSkip() {
        return skip;
    }

    public int getLimit() {
        return limit;
    }
}
