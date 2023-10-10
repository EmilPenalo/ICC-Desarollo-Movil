package basic.android.examen1.api;

import basic.android.examen1.dto.ProductList;
import basic.android.examen1.entity.Product;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("products")
    Call<ProductList> findAll();

    @GET("products/{id}")
    Call<Product> find(@Path("id") int id);

    @GET("products/search")
    Call<ProductList> searchProducts(@Query("q") String searchTerm);
}
