package basic.android.listadotareas.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import basic.android.listadotareas.R;
import basic.android.listadotareas.entity.TodoItem;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<TodoItem> itemList;
    private Context context;

    private Consumer<TodoItem> completedConsumer;
    public void setCompletedConsumer(Consumer<TodoItem> completedConsumer) {
        this.completedConsumer = completedConsumer;
    }

    private Consumer<TodoItem> deleteConsumer;
    public void setDeleteConsumer(Consumer<TodoItem> deleteConsumer) {
        this.deleteConsumer = deleteConsumer;
    }

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setItems(List<TodoItem> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (itemList == null || itemList.isEmpty()) {
            return;
        }
        TodoItem item = itemList.get(position);

        holder.title.setText(item.getTitle());

        int[] skyImageIds = {R.drawable.sky1, R.drawable.sky2, R.drawable.sky3, R.drawable.sky4, R.drawable.sky5, R.drawable.sky6};
        holder.imageView.setImageResource(skyImageIds[item.getPhotoId()]);

        holder.completedCbx.setOnCheckedChangeListener(null);
        holder.completedCbx.setChecked(item.isCompleted());

        if (item.isCompleted()) {
            holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.itemView.setAlpha(0.6F);
            holder.completedCbx.setText(R.string.completed);
        } else {
            holder.title.setPaintFlags(holder.title.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            holder.itemView.setAlpha(1);
            holder.completedCbx.setText("");
        }

        holder.deleteButton.setOnClickListener(view -> {
            showDeleteConfirmationDialog(item);
        });

        holder.completedCbx.setOnClickListener(view -> {
            if (completedConsumer != null) {
                completedConsumer.accept(item);
            }
        });
    }


    private void showDeleteConfirmationDialog(TodoItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Eliminar");
        builder.setMessage("¿Seguro que desea eliminar la tarea '" + item.getTitle() + "'?");

        builder.setPositiveButton("Sí", (dialog, which) -> {
            if (deleteConsumer != null) {
                deleteConsumer.accept(item);
            }

            dialog.dismiss();
        });

        builder.setNegativeButton("No", (dialog, which) -> {
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public int getItemCount() {
        if (itemList == null) {
            return 0;
        } else {
            return itemList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        TextView title;
        ImageButton deleteButton;
        CheckBox completedCbx;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.taskRecyclerTitle);
            deleteButton = itemView.findViewById(R.id.btnRecyclerDelete);
            imageView = itemView.findViewById(R.id.image);
            completedCbx = itemView.findViewById(R.id.completedCbx);
        }
    }

    private void showMessage(String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
