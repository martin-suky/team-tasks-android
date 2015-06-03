package cz.suky.teamtodo.android.service;

import java.util.List;

import cz.suky.teamtodo.android.model.TodoList;

/**
 * Created by suky on 3.6.15.
 */
public interface TodoListService {
    public List<TodoList> getAll();
}
