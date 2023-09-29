package basic.android.listadotareas.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import basic.android.listadotareas.R;
import basic.android.listadotareas.TodoItem;
import basic.android.listadotareas.ui.listView.ListViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends ArrayAdapter<TodoItem> {
    private List<TodoItem> itemList;

    public ListViewAdapter(@NonNull Context context, ArrayList<TodoItem> itemList) {
        super(context, 0, itemList);
        this.itemList = itemList;
    }

    public void setData(List<TodoItem> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;

        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TodoItem item = getItem(position);

        TextView title = currentItemView.findViewById(R.id.taskRecyclerTitle);
        title.setText(item.getTitle());

        ImageButton deleteButton = currentItemView.findViewById(R.id.btnRecyclerDelete);

        deleteButton.setOnClickListener(view -> {
            showDeleteConfirmationDialog(item);
        });

        return currentItemView;
    }

    private void showDeleteConfirmationDialog(TodoItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Eliminar");
        builder.setMessage("¿Seguro que desea eliminar la tarea '"+ item.getTitle() +"'?");

        builder.setPositiveButton("Sí", (dialog, which) -> {
            itemList.remove(item);
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
}
