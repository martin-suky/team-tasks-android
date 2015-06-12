package cz.suky.teamtasks.android.db.builder;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suky on 7.6.15.
 */
public class UpdateBuilder {
    private String tableName = "";
    private String where = "";
    private List<String> wheres = new ArrayList<>();

    public UpdateBuilder() {

    }

    private int execute(SQLiteDatabase db, ContentValues values) {
        return db.update(tableName, values, where, wheres.toArray(new String[]{}));
    }

    public Where table(String tableName) {
        this.tableName = tableName;
        return new Where(this);
    }

    public class Where {

        private UpdateBuilder parent;

        public Where(UpdateBuilder updateBuilder) {

            parent = updateBuilder;
        }

        public Done equals(String column, String value) {
            parent.where += column + " = ?";
            parent.wheres.add(value);
            return new Done(parent);
        }

    }

    public class Done {

        private UpdateBuilder parent;

        public Done(UpdateBuilder parent) {
            this.parent = parent;
        }

        public int execute(SQLiteDatabase db, ContentValues values) {
            return parent.execute(db, values);
        }

    }
}
