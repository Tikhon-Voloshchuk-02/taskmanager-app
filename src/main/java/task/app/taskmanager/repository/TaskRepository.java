package task.app.taskmanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import task.app.taskmanager.model.Task;

public interface TaskRepository extends MongoRepository<Task, String> {

}
