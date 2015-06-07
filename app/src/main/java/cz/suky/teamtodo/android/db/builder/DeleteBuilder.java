package cz.suky.teamtodo.android.db.builder;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suky on 7.6.15.
 */
public class DeleteBuilder {
    private String tableName;
    private String where;
    private List<String> wheres = new ArrayList<>();

    private int execute(SQLiteDatabase db) {
        return db.delete(tableName, where, wheres.toArray(new String[]{}));
    }

    public Where table(String tableName) {
        this.tableName = tableName;
        return new Where(this);
    }

    public class Where {

        private DeleteBuilder parent;

        public Where(DeleteBuilder updateBuilder) {

            parent = updateBuilder;
        }

        public Done equals(String column, String value) {
            parent.where += column + " = ?";
            parent.wheres.add(value);
            return new Done(parent);
        }

    }

    public class Done {

        private DeleteBuilder parent;

        public Done(DeleteBuilder parent) {
            this.parent = parent;
        }

        public int execute(SQLiteDatabase db) {
            return parent.execute(db);
        }

    }
}
