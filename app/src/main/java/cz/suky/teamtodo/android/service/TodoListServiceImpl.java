package cz.suky.teamtodo.android.service;

import java.util.Arrays;
import java.util.List;

import cz.suky.teamtodo.android.model.TodoList;

/**
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
}
