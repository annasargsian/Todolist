package main.controller;

import main.exception.BadRequestException;
import main.model.Task;
import main.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class ToDoController {

    @Autowired
    private TaskRepository taskRepository;


    @PostMapping("/")
    public Task addTask(@RequestBody Task task) {
        return taskRepository.addToDo(task);
    }

    @GetMapping("/")
    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable(name = "id") int id) {
        return taskRepository.getTask(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable(name = "id") int id, @RequestBody Task newTask) {
        return taskRepository.updateTask(id, newTask);
    }

    @PostMapping("/{id}/{action}")
    public Task onAction(@PathVariable(name = "id") int id, @PathVariable(name = "action") String action) {
        Task task = taskRepository.getTask(id);
        switch (action) {
            case "check":
                task.setCompleted(true);
                break;

            case "uncheck":
                task.setCompleted(false);
                break;

            default:
                throw new BadRequestException();
        }
        taskRepository.updateTask(id, task);
        return task;
    }

    @DeleteMapping("/{id}")
    public Task deleteTask(@PathVariable(name = "id") int id) {
        return taskRepository.deleteTask(id);
    }

}