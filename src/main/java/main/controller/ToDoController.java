package main.controller;

import main.model.Task;
import main.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/toDo")
public class ToDoController {

    private final TaskRepository taskRepository;

    public ToDoController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping()
    public int addTask(@RequestBody Task task) {
        return taskRepository.addToDo(task);
    }

    @GetMapping()
    public List<Task> showAllTasks() {
        return taskRepository.getAllTasks();
    }

    @GetMapping("{id}")
    public Task getTask(@PathVariable(name = "id") int id) {
        return taskRepository.getTask(id);
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable(name = "id") int id) {
        taskRepository.deleteTask(id);
    }

    @PutMapping("{id}")
    public boolean updateTask(@PathVariable(name = "id") int id, Task newTask) {
        return taskRepository.updateTask(id,newTask);
    }

}