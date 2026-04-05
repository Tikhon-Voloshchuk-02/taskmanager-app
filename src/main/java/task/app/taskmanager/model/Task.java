package task.app.taskmanager.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
public class Task {
    @Id
    private String id;

    //@NotBlank(message = "Task must not be blank")
    //@Size(min = 3, max = 50, message = "Text must be between 3 and 100")
    private String text;

    private boolean done;

    public Task () {}

    public Task(String text) {
        this.text = text;
        this.done = false;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }
    public boolean getDone(){
        return done;
    }

    public void setId(String taskId) {
        this.id = taskId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
