package cz.suky.teamtasks.android.db;

import android.content.ContentValues;
import android.database.Cursor;

import cz.suky.teamtasks.android.model.TaskList;

/**
 * Created by suky on 7.6.15.
 */
public class TaskListDaoImpl extends AbstractDaoImpl<TaskList> implements TaskListDao {

    public TaskListDaoImpl(TeamTasksDbHelper dbHelper) {
        super(dbHelper);
    }

    @Override
    protected String getTableName() {
        return TaskList.TABLE_NAME;
    }

    @Override
    protected String[] getAllColumns() {
        return new String[]{
                TaskList.COLUMN_ID,
                TaskList.COLUMN_VERSION,
                TaskList.COLUMN_NAME
        };
    }

    @Override
    protected TaskList mapToModel(Cursor cursor) {
        TaskList taskList = mapToModelAbstract(new TaskList(), cursor);
        taskList.setName(cursor.getString(cursor.getColumnIndex(TaskList.COLUMN_NAME)));
        return taskList;
    }

    @Override
    protected ContentValues mapToValues(TaskList model) {
        ContentValues contentValues = mapToValuesAbstract(model);
        contentValues.put(TaskList.COLUMN_NAME, model.getName());
        return contentValues;
    }
}
