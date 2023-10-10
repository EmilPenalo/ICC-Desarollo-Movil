package basic.android.examen1;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import basic.android.examen1.databinding.ActivityMainBinding;
import basic.android.examen1.entity.Product;
import basic.android.examen1.ui.ProductAdapter;
import basic.android.examen1.ui.ProductViewModel;

import java.util.function.Consumer;

public class MainActivity extends AppCompatActivity {
    private ProductViewModel viewModel;
    private ActivityMainBinding binding;

    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        RecyclerView recyclerView = binding.rv;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ProductAdapter(this);
        recyclerView.setAdapter(adapter);

        viewModel.getProductList().observe(this, items -> {
            adapter.setProducts(items);
        });

        adapter.setClickConsumer(new Consumer<Product>() {
            @Override
            public void accept(Product product) {
                showMessage("Showing: " + product.getTitle());

                Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
                intent.putExtra("productId", product.getId());
                startActivity(intent);
            }
        });

        ImageButton searchBtn = binding.searchBtn;
        searchBtn.setOnClickListener(v -> {
            String term = binding.searchTerm.getText().toString();
            showMessage("Buscando: " + term);
            viewModel.searchProducts(term);
        });
    }

    private void showMessage(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}