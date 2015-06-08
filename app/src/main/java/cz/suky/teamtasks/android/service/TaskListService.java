package cz.suky.teamtasks.android.service;

import java.util.List;

import cz.suky.teamtasks.android.model.Status;
import cz.suky.teamtasks.android.model.TaskList;

/**
 * So far simple service for manipulating todos.
 * Created by suky on 3.6.15.
 */
public interface TaskListService {
    void getAll(ServiceResultCallback<List<TaskList>> calback);

    void get(long id, ServiceResultCallback<TaskList> callback);

    void updateStatus(long id, Status newStatus, ServiceResultCallback<Void> callback);
}