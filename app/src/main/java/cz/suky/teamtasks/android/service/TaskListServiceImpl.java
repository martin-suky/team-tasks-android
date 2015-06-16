package cz.suky.teamtasks.android.service;

import android.content.Context;

import java.util.List;

import cz.suky.teamtasks.android.db.TaskListDao;
import cz.suky.teamtasks.android.db.builder.DeleteBuilder;
import cz.suky.teamtasks.android.model.Status;
import cz.suky.teamtasks.android.model.TaskList;

/**
 * Implementation of TaskListService
 * Created by suky on 3.6.15.
 */
public class TaskListServiceImpl implements TaskListService {

    private final TaskListDao taskListDao;

    public TaskListServiceImpl(TaskListDao taskListDao) {
        this.taskListDao = taskListDao;
    }

    @Override
    public void getAll(ServiceResultCallback<List<TaskList>> calback) {
        new AsyncService<List<TaskList>>(calback) {
            @Override
            protected Response<List<TaskList>> doIt() {
                return Response.ok(taskListDao.getAll());
            }
        }.execute();
    }

    @Override
    public void get(final int id, ServiceResultCallback<TaskList> callback) {
        new AsyncService<TaskList>(callback) {
            @Override
            protected Response<TaskList> doIt() {
                return Response.ok(taskListDao.getById(id));
            }
        }.execute();
    }

    @Override
    public void save(final TaskList taskList, ServiceResultCallback<Void> callback) {
        new AsyncService<Void>(callback) {
            @Override
            protected Response<Void> doIt() {
                taskListDao.save(taskList);
                return Response.ok();
            }
        }.execute();
    }

    @Override
    public void delete(final int id, ServiceResultCallback<Void> callback) {
        new AsyncService<Void>(callback) {

            @Override
            protected Response<Void> doIt() {
                taskListDao.delete(id);
                return Response.ok();
            }
        }.execute();
    }

}
