package cz.suky.teamtasks.android.service;

import android.content.Context;

import cz.suky.teamtasks.android.db.TaskValueDao;

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
    public void getCountOfTaskValues(Integer taskListId, ServiceResultCallback<Integer> callback) {
        new AsyncService<Integer, Integer>(context, callback) {

            @Override
            protected Integer doIt(Integer integer) {
                return taskValueDao.countOfValues(integer);
            }
        }.execute(taskListId);
    }
}
