package cz.suky.teamtasks.android.service;

import android.content.Context;

import java.util.List;

import cz.suky.teamtasks.android.db.TaskValueDao;
import cz.suky.teamtasks.android.model.Status;
import cz.suky.teamtasks.android.model.TaskValue;

/**
 * Created by msoukup on 6/12/2015.
 */
public class TaskValueServiceImpl implements TaskValueService {

    private final Context context;
    private final TaskValueDao taskValueDao;

    public TaskValueServiceImpl(Context context, TaskValueDao taskValueDao) {
        this.context = context;
        this.taskValueDao = taskValueDao;
    }

    @Override
    public void getAllForTaskListId(final Integer taskListId, ServiceResultCallback<List<TaskValue>> callback) {
        new AsyncService<List<TaskValue>>(context, callback) {

            @Override
            protected Response doIt() {
                return Response.ok(taskValueDao.getAllForTaskList(taskListId));
            }
        }.execute();
    }

    @Override
    public void getCountOfTaskValues(final Integer taskListId, ServiceResultCallback<Integer> callback) {
        new AsyncService<Integer>(context, callback) {

            @Override
            protected Response<Integer> doIt() {
                return Response.ok(taskValueDao.countOfValues(taskListId));
            }
        }.execute();
    }

    @Override
    public void get(final int taskValueId, ServiceResultCallback<TaskValue> serviceResultCallback) {
        new AsyncService<TaskValue>(context, serviceResultCallback) {

            @Override
            protected Response doIt() {
                return Response.ok(taskValueDao.getById(taskValueId));
            }
        }.execute();
    }

    @Override
    public void save(final TaskValue taskValue, ServiceResultCallback<Void> callback) {
        new AsyncService<Void>(context, callback) {

            @Override
            protected Response doIt() {
                taskValueDao.save(taskValue);
                return Response.ok();
            }
        }.execute();
    }

    @Override
    public void setStatus(final Integer taskValueId, final Status status, ServiceResultCallback<Void> callback) {
        new AsyncService<Void>(context, callback) {

            @Override
            protected Response doIt() {
                taskValueDao.setStatus(taskValueId, status);
                return Response.ok();
            }
        }.execute();
    }
}
