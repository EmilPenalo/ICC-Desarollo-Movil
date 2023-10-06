package basic.android.listadotareas.entity;

import android.support.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

@Entity(tableName = "todo_item")
public class TodoItem {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String title;
    private boolean completed;

    public TodoItem(@NonNull String title) {
        this.title = title;
        this.completed = false;

        this.photoId = randomPhotoId();
    }

    private int randomPhotoId() {
        Random random = new Random();
        return random.nextInt(6);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private int photoId;

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
