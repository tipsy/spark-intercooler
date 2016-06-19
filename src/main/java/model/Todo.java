package model;

import lombok.*;
import java.util.*;

@Data
@AllArgsConstructor
public class Todo {

    String title;
    String id;
    Status status;

    public void toggleStatus() {
        this.status = isComplete() ? Status.ACTIVE : Status.COMPLETE;
    }

    public boolean isComplete() {
        return this.status == Status.COMPLETE;
    }

    public static Todo create(String title) {
        return new Todo(title, UUID.randomUUID().toString(), Status.ACTIVE);
    }

}
