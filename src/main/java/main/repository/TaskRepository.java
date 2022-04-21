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
    private final AtomicInteger TASK_ID_HOLDER = new AtomicInteger();


    public int addToDo(Task task) {
        final int currentId = TASK_ID_HOLDER.incrementAndGet();
        task.setId(currentId);
        tasks.put(currentId, task);
        return currentId;
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

    public boolean updateTask(int taskId,Task task) {
        if (tasks.containsKey(taskId)) {
            task.setId(taskId);
            tasks.put(taskId, task);
            return true;
        }
        return false;
    }

    public void deleteTask(int taskId) {
        if (tasks.containsKey(taskId)) {
            tasks.remove(taskId);
        } else {
            throw new NotFoundException();
        }

    }
}
