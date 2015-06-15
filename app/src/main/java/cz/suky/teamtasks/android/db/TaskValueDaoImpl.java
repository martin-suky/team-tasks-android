package cz.suky.teamtasks.android.db;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

import cz.suky.teamtasks.android.db.builder.QueryBuilder;
import cz.suky.teamtasks.android.model.TaskValue;

/**
 * Created by msoukup on 6/12/2015.
 */
public class TaskValueDaoImpl extends AbstractDaoImpl<TaskValue> implements TaskValueDao {
    public TaskValueDaoImpl(TeamTasksDbHelper dbHelper) {
        super(dbHelper);
    }

    @Override
    protected String getTableName() {
        return TaskValue.TABLE_NAME;
    }

    @Override
    protected String[] getAllColumns() {
        return new String[]{
                TaskValue.COLUMN_ID,
                TaskValue.COLUMN_VERSION,
                TaskValue.COLUMN_TEXT,
                TaskValue.COLUMN_LIST_ID
        };
    }

    @Override
    protected TaskValue mapToModel(Cursor cursor) {
        TaskValue taskValue = mapToModelAbstract(new TaskValue(), cursor);
        taskValue.setText(cursor.getString(cursor.getColumnIndex(TaskValue.COLUMN_TEXT)));
        taskValue.setTaskListId(cursor.getInt(cursor.getColumnIndex(TaskValue.COLUMN_LIST_ID)));
        return taskValue;
    }

    @Override
    protected ContentValues mapToValues(TaskValue model) {
        ContentValues contentValues = mapToValuesAbstract(model);
        contentValues.put(TaskValue.COLUMN_TEXT, model.getText());
        contentValues.put(TaskValue.COLUMN_LIST_ID, model.getTaskListId());
        return contentValues;
    }

    @Override
    public List<TaskValue> getAllForTaskList(Integer taskListId) {
        Cursor cursor = new QueryBuilder().select(getAllColumns()).from(TaskValue.TABLE_NAME).where().equals(TaskValue.COLUMN_LIST_ID, taskListId.toString()).execute(getRDb());
        return mapToModels(cursor);
    }

    @Override
    public int countOfValues(Integer taskListId) {
        Cursor cursor = new QueryBuilder().select(TaskValue.COLUMN_ID).from(TaskValue.TABLE_NAME).where().equals(TaskValue.COLUMN_LIST_ID, taskListId.toString()).execute(getRDb());
        return cursor.getCount();
    }
}
