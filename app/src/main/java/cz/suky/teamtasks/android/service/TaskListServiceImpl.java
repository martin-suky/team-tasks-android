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

    private Context context;
    private final TaskListDao taskListDao;

    public TaskListServiceImpl(Context context, TaskListDao taskListDao) {
        this.context = context;
        this.taskListDao = taskListDao;
    }

    @Override
    public void getAll(ServiceResultCallback<List<TaskList>> calback) {
        new AsyncService<Void, List<TaskList>>(context, calback) {
            @Override
            protected List<TaskList> doIt(Void request) {
                return taskListDao.getAll();
            }
        }.execute();
    }

    @Override
    public void get(int id, ServiceResultCallback<TaskList> callback) {
        new AsyncService<Integer, TaskList>(context, callback) {
            @Override
            protected TaskList doIt(Integer request) {
                return taskListDao.getById(request);
            }
        }.execute(id);
    }

    @Override
    public void save(TaskList taskList, ServiceResultCallback<Void> callback) {
        new AsyncService<TaskList, Void>(context, callback) {
            @Override
            protected Void doIt(TaskList taskList) {
                taskListDao.save(taskList);
                return null;
            }
        }.execute(taskList);
    }

    @Override
    public void delete(int id, ServiceResultCallback<Void> callback) {
        new AsyncService<Integer, Void>(context, callback) {

            @Override
            protected Void doIt(Integer integer) {
                taskListDao.delete(integer);
                return null;
            }
        }.execute(id);
    }

}
