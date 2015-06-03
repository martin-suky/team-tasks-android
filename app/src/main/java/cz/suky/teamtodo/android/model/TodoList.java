package cz.suky.teamtodo.android.model;

import java.util.List;

/**
 * Created by suky on 2.6.15.
 */
public class TodoList extends AbstractModel {
    private static final long serialVersionUID = 1;

    private String name;

    private List<TodoValue> todoValues;

    public TodoList(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TodoValue> getTodoValues() {
        return todoValues;
    }

    public void setTodoValues(List<TodoValue> todoValues) {
        this.todoValues = todoValues;
    }
}
