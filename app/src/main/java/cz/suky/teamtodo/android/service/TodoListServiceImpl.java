package cz.suky.teamtodo.android.service;

import java.util.Arrays;
import java.util.List;

import cz.suky.teamtodo.android.model.Status;
import cz.suky.teamtodo.android.model.TodoList;
import cz.suky.teamtodo.android.model.TodoValue;

/**
 * Implementation of TodoListService
 * Created by suky on 3.6.15.
 */
public class TodoListServiceImpl implements TodoListService {

    @Override
    public List<TodoList> getAll() {
        return Arrays.asList(
                new TodoList("test1"),
                new TodoList("test11"),
                new TodoList("test12"),
                new TodoList("test13"),
                new TodoList("test14"),
                new TodoList("test15"),
                new TodoList("test16"),
                new TodoList("test17"),
                new TodoList("test18"),
                new TodoList("test19"),
                new TodoList("test2"));
    }

    @Override
    public TodoList get(long id) {
        TodoList todoList = new TodoList("Test 1");
        todoList.setTodoValues(Arrays.asList(
                new TodoValue("uklid", Status.OPEN),
                new TodoValue("nakup", Status.OPEN),
                new TodoValue("kolo", Status.DONE)
        ));
        return todoList;
    }

    @Override
    public void updateStatus(long id, Status newStatus) {

    }
}
