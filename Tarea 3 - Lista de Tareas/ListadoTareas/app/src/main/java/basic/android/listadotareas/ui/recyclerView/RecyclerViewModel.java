package basic.android.listadotareas.ui.recyclerView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import basic.android.listadotareas.TodoItem;
import basic.android.listadotareas.ui.listView.ListViewModel;

import java.util.ArrayList;

public class RecyclerViewModel extends ViewModel {

    private static RecyclerViewModel instance;

    private final ArrayList<TodoItem> todoItemList;

    private RecyclerViewModel() {
        todoItemList = new ArrayList<>();
        generateFakeItems();
    }

    public static RecyclerViewModel getInstance() {
        if (instance == null) {
            instance = new RecyclerViewModel();
        }
        return instance;
    }

    public ArrayList<TodoItem> getTodoItems() {
        return todoItemList;
    }

    public void addTodoItem(TodoItem item) {
        todoItemList.add(item);
    }

    public void generateFakeItems() {
        TodoItem item1 = new TodoItem("Tarea 1");
        TodoItem item2 = new TodoItem("Tarea 2");
        TodoItem item3 = new TodoItem("Tarea 3");

        todoItemList.add(item1);
        todoItemList.add(item2);
        todoItemList.add(item3);
    }

    public void removeItem(int position) {
        if (position >= 0 && position < todoItemList.size()) {
            todoItemList.remove(position);
        }
    }

    public void removeItem(TodoItem item) {
        todoItemList.remove(item);
    }
}