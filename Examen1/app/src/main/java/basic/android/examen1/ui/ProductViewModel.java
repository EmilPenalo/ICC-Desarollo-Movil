package basic.android.examen1.ui;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import basic.android.examen1.ProductRepository;
import basic.android.examen1.entity.Product;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private final ProductRepository repository;
    private MutableLiveData<List<Product>> productList;

    public ProductViewModel(@NotNull Application application) {
        super(application);
        this.repository = new ProductRepository(application);
        productList = repository.getProductList();
    }

    public LiveData<List<Product>> getProductList() {
        return productList;
    }

    public void searchProducts(String term) {
        repository.searchProducts(term, productList);
    }

    public LiveData<Product> getProductById(int id) {
        MutableLiveData<Product> productLiveData = new MutableLiveData<>();
        repository.getProductById(id, productLiveData);
        return productLiveData;
    }
}
