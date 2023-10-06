package basic.android.listadotareas.viewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import basic.android.listadotareas.entity.TodoItem;
import basic.android.listadotareas.database.ItemRepository;

import java.util.List;

public class RecyclerViewModel extends AndroidViewModel {

    private final ItemRepository repository;

    private LiveData<List<TodoItem>> todoItemList;

    public RecyclerViewModel(Application application) {
        super(application);
        repository = new ItemRepository(application);
        todoItemList = repository.getItemList();
    }

    public LiveData<List<TodoItem>> getTodoItems() {
        return todoItemList;
    }

    public void newItem(String title) {
        TodoItem item = new TodoItem(title);
        repository.insert(item);
    }

    public void removeItem(TodoItem item) {
        repository.delete(item);
    }

    public void updateCompleted(TodoItem item, boolean completed) {
        item.setCompleted(completed);
        repository.update(item);
    }
}