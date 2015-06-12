package cz.suky.teamtasks.android.db;

import cz.suky.teamtasks.android.model.TaskValue;

/**
 * Created by msoukup on 6/12/2015.
 */
public interface TaskValueDao extends AbstractDao<TaskValue> {
    int countOfValues(Integer taskListId);
}
