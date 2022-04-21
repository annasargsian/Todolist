package main.repository;

import main.exception.NotFoundException;
import main.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TaskRepository {

    private final Map<Integer, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger();


    public Task addToDo(Task task) {
        final int currentId = counter.incrementAndGet();
        task.setId(currentId);
        tasks.put(currentId, task);
        return task;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public Task getTask(int taskId) {
        if (tasks.containsKey(taskId)) {
            return tasks.get(taskId);
        }
        throw new NotFoundException();
    }

    public Task updateTask(int taskId, Task task) {
        if (tasks.containsKey(taskId)) {
            task.setId(taskId);
            tasks.put(taskId, task);
            return task;
        }
        throw new NotFoundException();
    }

    public Task deleteTask(int taskId) {
        if (tasks.containsKey(taskId)) {
            return tasks.remove(taskId);
        }
        throw new NotFoundException();

    }
}
