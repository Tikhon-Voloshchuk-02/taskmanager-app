package task.app.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskCreateDto {

    @NotBlank(message = "Text must not be blank")
    @Size(min = 3, max = 100, message = "Text must be between 3 and 100 characters")
    private String text;

    private boolean done;

    public TaskCreateDto() {}

    public String getText() {
        return text;
    }

    public boolean isDone() {
        return done;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
