package cz.suky.teamtodo.android.service;

import java.util.List;

import cz.suky.teamtodo.android.model.Status;
import cz.suky.teamtodo.android.model.TodoList;

/**
 * So far simple service for manipulating todos.
 * Created by suky on 3.6.15.
 */
public interface TodoListService {
    void getAll(ServiceResultCallback<List<TodoList>> calback);

    void get(long id, ServiceResultCallback<TodoList> callback);

    void updateStatus(long id, Status newStatus, ServiceResultCallback<Void> callback);
}
