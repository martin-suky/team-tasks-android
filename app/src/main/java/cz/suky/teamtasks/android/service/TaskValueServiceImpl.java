package cz.suky.teamtasks.android.service;

import android.content.Context;

import java.util.List;

import cz.suky.teamtasks.android.db.TaskValueDao;
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
    public void getAllForTaskListId(Integer taskListId, ServiceResultCallback<List<TaskValue>> callback) {
        new AsyncService<Integer, List<TaskValue>>(context, callback) {

            @Override
            protected Response doIt(Integer integer) {
                return Response.ok(taskValueDao.getAllForTaskList(integer));
            }
        }.execute(taskListId);
    }

    @Override
    public void getCountOfTaskValues(Integer taskListId, ServiceResultCallback<Integer> callback) {
        new AsyncService<Integer, Integer>(context, callback) {

            @Override
            protected Response<Integer> doIt(Integer integer) {
                return Response.ok(taskValueDao.countOfValues(integer));
            }
        }.execute(taskListId);
    }

    @Override
    public void get(int taskValueId, ServiceResultCallback<TaskValue> serviceResultCallback) {
        new AsyncService<Integer, TaskValue>(context, serviceResultCallback) {

            @Override
            protected Response doIt(Integer integer) {
                return Response.ok(taskValueDao.getById(integer));
            }
        }.execute(taskValueId);
    }

    @Override
    public void save(TaskValue taskValue, ServiceResultCallback<Void> callback) {
        new AsyncService<TaskValue, Void>(context, callback) {

            @Override
            protected Response doIt(TaskValue taskValue) {
                taskValueDao.save(taskValue);
                return Response.ok();
            }
        }.execute(taskValue);
    }
}
