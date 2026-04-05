package task.app.taskmanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import task.app.taskmanager.model.Task;
import task.app.taskmanager.service.TaskService;
import task.app.taskmanager.dto.TaskCreateDto;
import task.app.taskmanager.dto.TaskResponseDto;
import task.app.taskmanager.dto.TaskUpdateDto;


import java.util.List;

@RestController
@RequestMapping("/tasks") //все методы внутри -> начинаются с "/tasks"
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<Task> getAllTasks(){
        System.out.println("List of All Tasks");
        return service.getAllTasks();
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id){
        return ResponseEntity.ok(service.getTaskById(id)); //обработка ошибок
    }

    //  @RequestBody: Берёт - JSON из запроса и превращает его в Java-объект

    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@Valid @RequestBody TaskCreateDto dto) {
        return ResponseEntity.ok(service.addTask(dto));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable String id,@Valid  @RequestBody TaskUpdateDto dto){
        return ResponseEntity.ok(service.updateTask(id, dto));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable String id){
        service.deleteTask(id);
        return ResponseEntity.ok("Task deleted");
    }
}
