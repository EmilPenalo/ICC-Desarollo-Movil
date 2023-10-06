package basic.android.listadotareas.database;

import android.app.Application;
import androidx.lifecycle.LiveData;
import basic.android.listadotareas.entity.TodoItem;

import java.util.List;

public class ItemRepository {
    private TodoItemDao todoItemDao;
    private LiveData<List<TodoItem>> itemList;

    public ItemRepository(Application application) {
        TodoItemDatabase database = TodoItemDatabase.getInstance(application);
        todoItemDao = database.todoItemDao();
        itemList = todoItemDao.getAllTodoItems();
    }

    public LiveData<List<TodoItem>> getItemList() {
        return itemList;
    }

    public void insert(TodoItem todoItem) {
        TodoItemDatabase.databaseWriteExecutor.execute(() -> {
            todoItemDao.insert(todoItem);
        });
    }

    public void update(TodoItem todoItem) {
        TodoItemDatabase.databaseWriteExecutor.execute(() -> {
            todoItemDao.update(todoItem);
        });
    }

    public void delete(TodoItem todoItem) {
        TodoItemDatabase.databaseWriteExecutor.execute(() -> {
            todoItemDao.delete(todoItem);
        });
    }
}
