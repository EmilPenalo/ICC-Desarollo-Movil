package basic.android.listadotareas.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import basic.android.listadotareas.entity.TodoItem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {TodoItem.class}, version = 1, exportSchema = false)
public abstract class TodoItemDatabase extends RoomDatabase {
    private static TodoItemDatabase instance;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract TodoItemDao todoItemDao();

    public static synchronized TodoItemDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            TodoItemDatabase.class, "todo_item_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}