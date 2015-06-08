package cz.suky.teamtasks.android.service;

import java.util.List;

import cz.suky.teamtasks.android.db.TaskListDao;
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
        new AsyncService<Void, List<TaskList>>(calback) {
            @Override
            protected List<TaskList> doIt(Void request) {
                return taskListDao.getAll();
            }
        }.execute();
    }

    @Override
    public void get(long id, ServiceResultCallback<TaskList> callback) {
        new AsyncService<Long, TaskList>(callback) {
            @Override
            protected TaskList doIt(Long request) {
                return taskListDao.getById(request);
            }
        }.execute(id);
    }

    @Override
    public void updateStatus(long id, Status newStatus, ServiceResultCallback<Void> callback) {

    }
}
