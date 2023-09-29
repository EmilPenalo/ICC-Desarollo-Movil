package basic.android.listadotareas.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import basic.android.listadotareas.R;
import basic.android.listadotareas.TodoItem;
import basic.android.listadotareas.ui.listView.ListViewModel;
import basic.android.listadotareas.ui.recyclerView.RecyclerViewModel;


import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<TodoItem> itemList;
    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<TodoItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    public void setData(List<TodoItem> itemList) {
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
        TodoItem item = itemList.get(position);

        holder.title.setText(item.getTitle());

        int[] skyImageIds = {R.drawable.sky1, R.drawable.sky2, R.drawable.sky3, R.drawable.sky4, R.drawable.sky5, R.drawable.sky6};
        holder.imageView.setImageResource(skyImageIds[item.getPhotoId()]);

        holder.deleteButton.setOnClickListener(view -> {
            showDeleteConfirmationDialog(item);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        TextView title;
        ImageButton deleteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.taskRecyclerTitle);
            deleteButton = itemView.findViewById(R.id.btnRecyclerDelete);
            imageView = itemView.findViewById(R.id.image);
        }
    }

    private void showDeleteConfirmationDialog(TodoItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Eliminar");
        builder.setMessage("¿Seguro que desea eliminar la tarea '" + item.getTitle() + "'?");

        builder.setPositiveButton("Sí", (dialog, which) -> {
            itemList.remove(item);
            RecyclerViewModel.getInstance().removeItem(item);
            ListViewModel.getInstance().removeItem(item);
            notifyDataSetChanged();

            dialog.dismiss();
        });

        builder.setNegativeButton("No", (dialog, which) -> {
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showMessage(String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
