package cz.suky.teamtasks.android.db;

import java.util.List;

import cz.suky.teamtasks.android.model.Status;
import cz.suky.teamtasks.android.model.TaskValue;

/**
 * Created by msoukup on 6/12/2015.
 */
public interface TaskValueDao extends AbstractDao<TaskValue> {

    List<TaskValue> getAllForTaskList(Integer taskListId);

    int countOfValues(Integer taskListId);

    void setStatus(Integer taskValueId, Status status);
}
