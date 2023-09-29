package basic.android.listadotareas;

import java.util.Random;

public class TodoItem {
    private String title;
    private boolean completed;

    private int photoId;

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public TodoItem(String title) {
        this.title = title;
        this.completed = false;

        this.photoId = randomPhotoId();
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    private int randomPhotoId() {
        Random random = new Random();
        return random.nextInt(6);
    }

}
