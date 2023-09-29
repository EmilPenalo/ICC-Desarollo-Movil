package basic.android.listadotareas.ui.listView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import basic.android.listadotareas.TodoItem;
import basic.android.listadotareas.adapters.ListViewAdapter;
import basic.android.listadotareas.databinding.FragmentListBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListFragment extends Fragment {

    private FragmentListBinding binding;
    private ListViewModel viewModel;
    private ListViewAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = ListViewModel.getInstance();

        binding = FragmentListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ListView listView = binding.listView;

        adapter = new ListViewAdapter(requireContext(), viewModel.getTodoItems());
        listView.setAdapter(adapter);

        FloatingActionButton addBtn = binding.addListBtn;
        addBtn.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.requireContext());

            builder.setTitle("Nueva Tarea");
            builder.setMessage("Nombre de la tarea:");

            LinearLayout layout = new LinearLayout(this.getContext());
            layout.setOrientation(LinearLayout.VERTICAL);

            EditText input = new EditText(this.getContext());

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(48, 0, 48, 0);
            input.setLayoutParams(layoutParams);

            layout.addView(input);
            builder.setView(layout);

            builder.setNegativeButton("Cancelar", (dialog, which) -> {
                dialog.cancel();
            });

            builder.setPositiveButton("OK", (dialog, which) -> {
                String taskName = input.getText().toString();

                if (!taskName.isEmpty()) {
                    TodoItem newItem = new TodoItem(taskName);
                    viewModel.addTodoItem(newItem);

                    adapter.setData(viewModel.getTodoItems());
                }

                dialog.dismiss();
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showMessage(String message) {
        Toast toast = Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }
}