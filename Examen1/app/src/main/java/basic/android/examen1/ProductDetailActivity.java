package basic.android.examen1;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import basic.android.examen1.databinding.ActivityProductDetailBinding;
import basic.android.examen1.entity.Product;
import basic.android.examen1.ui.ProductViewModel;
import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends AppCompatActivity {
    private ActivityProductDetailBinding binding;
    private ProductViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        int productId = getIntent().getIntExtra("productId", -1);

        viewModel.getProductById(productId).observe(this, product -> {
            if (product != null) {
                binding.titleTxt.setText(product.getTitle());
                binding.descriptionTxt.setText(product.getDescription());
                binding.brandTxt.setText("Marca: " + product.getBrand());
                binding.categoryTxt.setText("Categoria: " + product.getCategory());
                binding.priceTxt.setText("$" + product.getPrice());
                binding.discountPercentageTxt.setText("-" + product.getDiscountPercentage() + "%");
                binding.ratingTxt.setText("Rating: " + product.getRating());
                binding.stockTxt.setText("Stock: " + product.getStock());

                Picasso.get().load(product.getThumbnail()).into(binding.image);
            }
        });
    }

    private void showMessage(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}