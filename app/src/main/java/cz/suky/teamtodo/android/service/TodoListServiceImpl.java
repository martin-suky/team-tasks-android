package cz.suky.teamtodo.android.service;

import java.util.List;

import cz.suky.teamtodo.android.db.TodoListDao;
import cz.suky.teamtodo.android.model.Status;
import cz.suky.teamtodo.android.model.TodoList;

/**
 * Implementation of TodoListService
 * Created by suky on 3.6.15.
 */
public class TodoListServiceImpl implements TodoListService {

    private final TodoListDao todoListDao;

    public TodoListServiceImpl(TodoListDao todoListDao) {
        this.todoListDao = todoListDao;
    }

    @Override
    public void getAll(ServiceResultCallback<List<TodoList>> calback) {
        new AsyncService<Void, List<TodoList>>(calback) {
            @Override
            protected List<TodoList> doIt(Void request) {
                return todoListDao.getAll();
            }
        }.execute();
    }

    @Override
    public void get(long id, ServiceResultCallback<TodoList> callback) {
        new AsyncService<Long, TodoList>(callback) {
            @Override
            protected TodoList doIt(Long request) {
                return todoListDao.getById(request);
            }
        }.execute(id);
    }

    @Override
    public void updateStatus(long id, Status newStatus, ServiceResultCallback<Void> callback) {

    }
}
