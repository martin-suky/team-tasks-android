package cz.suky.teamtodo.android.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suky on 6.6.15.
 */
public class QueryBuilder {
    private String[] columns;
    private String   tableName;
    private String       where  = "";
    private List<String> wheres = new ArrayList<>();

    public QueryBuilder() {
    }

    private Cursor execute(SQLiteDatabase db) {
        return db.query(tableName, columns, where, wheres.toArray(new String[]{}), null, null, null);
    }

    public Select select(final String... columns) {
        this.columns = columns;
        return new Select(this);
    }

    public class Select {
        private QueryBuilder parent;

        private Select(QueryBuilder queryBuilder) {
            parent = queryBuilder;
        }

        public From from(final String tableName) {
            parent.tableName = tableName;
            return new From(parent);
        }
    }

    public class From {

        private QueryBuilder parent;

        private From(QueryBuilder parent) {
            this.parent = parent;
        }

        public Where where() {
            return new Where(parent);
        }

        public Cursor execute(SQLiteDatabase db) {
            return parent.execute(db);
        }

    }

    public class Where {

        private QueryBuilder parent;

        private Where(QueryBuilder parent) {
            this.parent = parent;
        }

        public Where equals(String column, String value) {
            parent.where += column + " = ?";
            parent.wheres.add(value);
            return this;
        }

        public Where and() {
            parent.where += " AND ";
            return this;
        }

        public Where or() {
            parent.where += " OR ";
            return this;
        }

        public Cursor execute(SQLiteDatabase db) {
            return parent.execute(db);
        }
    }
}
