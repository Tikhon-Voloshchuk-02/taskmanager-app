package task.app.taskmanager.service;

import org.springframework.stereotype.Service;
import task.app.taskmanager.dto.TaskCreateDto;
import task.app.taskmanager.dto.TaskResponseDto;
import task.app.taskmanager.dto.TaskUpdateDto;
import task.app.taskmanager.exception.TaskNotFoundException;
import task.app.taskmanager.model.Task;
import task.app.taskmanager.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository){
        this.repository=repository;
    }

    public List<Task> getAllTasks(){
        return repository.findAll();
    }

    public Task getTaskById(String id){
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));
    }

    public TaskResponseDto addTask(TaskCreateDto dto) {
        Task task = new Task();
        task.setText(dto.getText());
        task.setDone(dto.isDone());
        Task savedTask = repository.save(task);

        return mapToDto(savedTask);
    }

    public TaskResponseDto updateTask(String id, TaskUpdateDto dto){
        Task task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        task.setText(dto.getText());
        task.setDone(dto.isDone());

        Task updatedTask = repository.save(task);

        return mapToDto(updatedTask);
    }

    public void deleteTask(String id){
        if(!repository.existsById(id)){
            throw new TaskNotFoundException("Task with id " + id + "not found");
        }
        repository.deleteById(id);
    }

    private TaskResponseDto mapToDto(Task task) {
        return new TaskResponseDto(
                task.getId(),
                task.getText(),
                task.isDone()
        );
    }

}
