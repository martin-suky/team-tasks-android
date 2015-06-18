package cz.suky.teamtasks.android.service;

import java.util.List;

import cz.suky.teamtasks.android.model.Status;
import cz.suky.teamtasks.android.model.TaskValue;

/**
 * Created by msoukup on 6/12/2015.
 */
public interface TaskValueService {

    void getAllForTaskListId(Integer taskListId, ServiceResultCallback<List<TaskValue>> callback);

    void getCountOfTaskValues(Integer taskListId, ServiceResultCallback<Integer> callback);

    void get(int taskValueId, ServiceResultCallback<TaskValue> serviceResultCallback);

    void save(TaskValue taskValue, ServiceResultCallback<Void> callback);

    void setStatus(Integer taskValueId, Status status, ServiceResultCallback<Void> callback);

    void delete(Integer id, ExceptionHandlingCallback<Void> exceptionHandlingCallback);
}
