package basic.android.examen1;

import android.app.Application;
import androidx.lifecycle.MutableLiveData;
import basic.android.examen1.api.ApiClient;
import basic.android.examen1.api.ApiInterface;
import basic.android.examen1.dto.ProductList;
import basic.android.examen1.entity.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class ProductRepository {
    private MutableLiveData<List<Product>> productList;

    public ProductRepository(Application application) {
        productList = new MutableLiveData<>();
        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);

        api.findAll().enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productList.setValue(response.body().getProducts());
                }
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {

            }
        });
    }
    public MutableLiveData<List<Product>> getProductList() {
        return productList;
    }

    public void getProductById(int id, final MutableLiveData<Product> product) {
        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        api.find(id).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful() && response.body() != null) {
                    product.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
            }
        });
    }

    public void searchProducts(String searchTerm, final MutableLiveData<List<Product>> products) {
        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);

        api.searchProducts(searchTerm).enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                if (response.isSuccessful() && response.body() != null) {
                    products.setValue(response.body().getProducts());
                }
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
            }
        });
    }
}
