package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {
    private static final Map<Integer, Task> tasks = new HashMap<>();
    private static int currentId = 1;

    public static void increment(){
        currentId++;
    }

    public static int addToDo(Task task) {
        tasks.put(currentId, task);
        increment();
        return currentId;
    }

    public static List<Task> getAllTasks() {
        List<Task> toDoList = new ArrayList<>();
        toDoList.addAll(tasks.values());
        return toDoList;
    }

    public static Task getTask(int taskId) {
        if (tasks.containsKey(taskId)) {
            return tasks.get(taskId);
        }
        return null;
    }

    public static void changeTask(int taskId, Task task) {
        if (tasks.containsKey(taskId)) {
            tasks.replace(taskId, task);
        }
    }

    public static void deleteTask(int taskId) {
        tasks.remove(taskId);
    }
}
