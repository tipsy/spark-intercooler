package model;

import java.util.*;
import java.util.stream.*;

public class TodoDao {

    private static final List<Todo> DATA = new ArrayList<>();

    public static void add(Todo todo) {
        DATA.add(todo);
    }

    public static Todo find(String id) {
        return DATA.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

    public static void update(String id, String title) {
        find(id).setTitle(title);
    }

    public static List<Todo> ofStatus(String statusString) {
        return (statusString == null || statusString.isEmpty()) ? DATA : ofStatus(Status.valueOf(statusString.toUpperCase()));
    }

    public static List<Todo> ofStatus(Status status) {
        return DATA.stream().filter(t -> t.getStatus().equals(status)).collect(Collectors.toList());
    }

    public static void remove(String id) {
        DATA.remove(find(id));
    }

    public static void removeCompleted() {
        ofStatus(Status.COMPLETE).forEach(t -> TodoDao.remove(t.getId()));
    }

    public static void toggleStatus(String id) {
        find(id).toggleStatus();
    }

    public static void toggleAll(boolean complete) {
        TodoDao.all().forEach(t -> t.setStatus(complete ? Status.COMPLETE : Status.ACTIVE));
    }

    public static List<Todo> all() {
        return DATA;
    }
}
