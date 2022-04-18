package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/toDo")
public class ToDoController {

    @PostMapping(value = "/toDo/")
    public int addTask(@RequestBody Task task) {
        return Storage.addToDo(task);
    }

    @GetMapping(value = "/toDo/")
    public  List<Task> showAllTasks() {
        return Storage.getAllTasks();
    }

    @GetMapping(value = "/toDo/{id}")
    public  ResponseEntity getTask(@PathVariable int id) {
        Task task = Storage.getTask(id);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return new ResponseEntity(task, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/toDo/{id}")
    public ResponseEntity deleteTask(@PathVariable int id) {
        Task task = Storage.getTask(id);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            Storage.deleteTask(id);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @PatchMapping(value = "/toDo/{id}")
    public ResponseEntity changeTask(@PathVariable int id, Task newTask) {
        Task task = Storage.getTask(id);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            Storage.changeTask(id, newTask);
            return new ResponseEntity(HttpStatus.OK);
        }
    }
}
