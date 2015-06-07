package cz.suky.teamtodo.android.db;

import android.content.ContentValues;
import android.database.Cursor;

import cz.suky.teamtodo.android.model.TodoList;

/**
 * Created by suky on 7.6.15.
 */
public class TodoListDaoImpl extends AbstractDaoImpl<TodoList> implements TodoListDao {

    public TodoListDaoImpl(TodoDbHelper dbHelper) {
        super(dbHelper);
    }

    @Override
    protected String getTableName() {
        return TodoList.TABLE_NAME;
    }

    @Override
    protected String[] getAllColumns() {
        return new String[]{
                TodoList.COLUMN_ID,
                TodoList.COLUMN_VERSION,
                TodoList.COLUMN_NAME
        };
    }

    @Override
    protected TodoList mapToModel(Cursor cursor) {
        TodoList todoList = mapToModelAbstract(new TodoList(), cursor);
        todoList.setName(cursor.getString(cursor.getColumnIndex(TodoList.COLUMN_NAME)));
        return todoList;
    }

    @Override
    protected ContentValues mapToValues(TodoList model) {
        ContentValues contentValues = mapToValuesAbstract(model);
        contentValues.put(TodoList.COLUMN_NAME, model.getName());
        return contentValues;
    }
}
