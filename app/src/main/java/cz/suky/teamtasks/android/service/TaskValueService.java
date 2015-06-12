package cz.suky.teamtasks.android.service;

/**
 * Created by msoukup on 6/12/2015.
 */
public interface TaskValueService {
    void getCountOfTaskValues(Integer taskListId, ServiceResultCallback<Integer> callback);
}
