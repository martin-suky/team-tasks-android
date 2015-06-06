package cz.suky.teamtodo.android.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by suky on 7.6.15.
 */
public class InsertBuilder {

    private String table;
    ContentValues contentValues = new ContentValues();

    public InsertBuilder() {

    }

    public Value table(String table) {
        this.table = table;
        return new Value(this);
    }

    private long execute(SQLiteDatabase db) {
        return db.insert(table, null, contentValues);
    }

    public class Value {

        private InsertBuilder parent;

        private Value(InsertBuilder parent) {
            this.parent = parent;
        }

        public Value value(String column, String value) {
            contentValues.put(column, value);
            return this;
        }

        public Value value(String column, Long value) {
            contentValues.put(column, value);
            return this;
        }

        public long execute(SQLiteDatabase db) {
            return parent.execute(db);
        }

        public long execute(SQLiteDatabase db, ContentValues values) {
            contentValues = values;
            return parent.execute(db);
        }

    }
}
