package basic.android.listadotareas.database;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import basic.android.listadotareas.entity.TodoItem;

import java.util.List;

@Dao
public interface TodoItemDao {
    @Insert
    void insert(TodoItem todoItem);

    @Update
    void update(TodoItem todoItem);

    @Delete
    void delete(TodoItem todoItem);

    @Query("SELECT * FROM todo_item")
    LiveData<List<TodoItem>> getAllTodoItems();
}
