package basic.android.examen1.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import basic.android.examen1.ProductRepository;
import basic.android.examen1.R;
import basic.android.examen1.entity.Product;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> productList;
    private Context context;

    public void setClickConsumer(Consumer<Product> clickConsumer) {
        this.clickConsumer = clickConsumer;
    }

    private Consumer<Product> clickConsumer;

    public ProductAdapter(Context context) {
        this.context = context;
    }

    public void setProducts(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductAdapter.ViewHolder holder, int position) {
        if (productList == null || productList.isEmpty()) {
            return;
        }
        Product item = productList.get(position);

        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());

        Picasso.get().load(item.getThumbnail()).into(holder.imageView);

        holder.itemView.setOnClickListener(view -> {
            if (clickConsumer != null) {
                clickConsumer.accept(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (productList == null) {
            return 0;
        } else {
            return productList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        TextView title;
        TextView description;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.productTitle);
            description = itemView.findViewById(R.id.productDesc);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
