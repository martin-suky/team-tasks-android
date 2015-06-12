package cz.suky.teamtasks.android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cz.suky.teamtasks.android.model.TaskList;
import cz.suky.teamtasks.android.model.TaskValue;

/**
 * Created by suky on 6.6.15.
 */
public class TeamTasksDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME    = "TeamTasks.db";
    private static final int    DATABASE_VERSION = 1;

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";

    public TeamTasksDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DROP_TABLE + TaskList.TABLE_NAME);
        db.execSQL("CREATE TABLE " + TaskList.TABLE_NAME + " (" +
                TaskList.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TaskList.COLUMN_VERSION + " NUMERIC, " +
                TaskList.COLUMN_NAME + " TEXT NOT NULL" +
                ")");
        db.execSQL(DROP_TABLE + TaskValue.TABLE_NAME);
        db.execSQL("CREATE TABLE " + TaskValue.TABLE_NAME + " (" +
                TaskValue.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TaskValue.COLUMN_VERSION + " NUMERIC, " +
                TaskValue.COLUMN_TEXT + " TEXT NOT NULL, " +
                TaskValue.COLUMN_LIST_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY ("+ TaskValue.COLUMN_LIST_ID + ") REFERENCES " + TaskList.TABLE_NAME + " (" + TaskList.COLUMN_ID + ")" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
