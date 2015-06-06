package cz.suky.teamtodo.android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by suky on 6.6.15.
 */
public class TodoDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME    = "TeamTodo.db";
    private static final int    DATABASE_VERSION = 1;

    public TodoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
