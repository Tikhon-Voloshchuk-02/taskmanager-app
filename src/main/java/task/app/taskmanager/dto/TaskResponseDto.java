package task.app.taskmanager.dto;

public class TaskResponseDto {

    private String id;
    private String text;
    private boolean done;

    public TaskResponseDto() {}

    public TaskResponseDto(String id, String text, boolean done) {
        this.id = id;
        this.text = text;
        this.done = done;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isDone() {
        return done;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
