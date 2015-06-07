package cz.suky.teamtodo.android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cz.suky.teamtodo.android.model.TodoList;

/**
 * Created by suky on 6.6.15.
 */
public class TodoDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME    = "TeamTodo.db";
    private static final int    DATABASE_VERSION = 1;

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";

    public TodoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DROP_TABLE + TodoList.TABLE_NAME);
        db.execSQL("CREATE TABLE " + TodoList.TABLE_NAME + " (" +
                TodoList.COLUMN_ID + " NUMERIC PRIMARY KEY AUTOINCREMENT, " +
                TodoList.COLUMN_VERSION + " NUMERIC, " +
                TodoList.COLUMN_NAME + " TEXT NOT NULL" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
