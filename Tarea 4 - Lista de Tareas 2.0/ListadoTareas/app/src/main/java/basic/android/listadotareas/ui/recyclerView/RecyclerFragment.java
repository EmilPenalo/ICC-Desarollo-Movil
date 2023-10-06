package basic.android.listadotareas.ui.recyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import basic.android.listadotareas.viewModel.RecyclerViewModel;
import basic.android.listadotareas.entity.TodoItem;
import basic.android.listadotareas.adapters.RecyclerViewAdapter;
import basic.android.listadotareas.databinding.FragmentRecyclerBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.function.Consumer;

public class RecyclerFragment extends Fragment {

    private RecyclerViewModel viewModel;
    private RecyclerViewAdapter adapter;
    private FragmentRecyclerBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(RecyclerViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentRecyclerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new RecyclerViewAdapter(requireContext());
        recyclerView.setAdapter(adapter);

        viewModel.getTodoItems().observe(getViewLifecycleOwner(), items -> {
            adapter.setItems(items);
        });

        adapter.setCompletedConsumer(new Consumer<TodoItem>() {
            @Override
            public void accept(TodoItem item) {
                viewModel.updateCompleted(item, !item.isCompleted());
            }
        });

        adapter.setDeleteConsumer(new Consumer<TodoItem>() {
            @Override
            public void accept(TodoItem item) {
                viewModel.removeItem(item);
            }
        });

        FloatingActionButton addBtn = binding.addRecyclerBtn;
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
                    viewModel.newItem(taskName);
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