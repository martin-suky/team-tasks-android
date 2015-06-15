package cz.suky.teamtasks.android.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cz.suky.teamtasks.android.db.builder.DeleteBuilder;
import cz.suky.teamtasks.android.db.builder.InsertBuilder;
import cz.suky.teamtasks.android.db.builder.QueryBuilder;
import cz.suky.teamtasks.android.db.builder.UpdateBuilder;
import cz.suky.teamtasks.android.model.AbstractModel;

/**
 * Created by suky on 6.6.15.
 */
public abstract class AbstractDaoImpl<Model extends AbstractModel> implements AbstractDao<Model> {

    private final TeamTasksDbHelper dbHelper;

    public AbstractDaoImpl(TeamTasksDbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public Model getById(int id) {
        SQLiteDatabase rDb = getRDb();
        Cursor cursor = new QueryBuilder()
                .select(getAllColumns())
                .from(getTableName())
                .where()
                .equals(AbstractModel.COLUMN_ID, id + "")
                .execute(rDb);

        if (cursor.getCount() == 1 && cursor.moveToFirst()) {
            return mapToModel(cursor);
        } else {
            throw new IllegalArgumentException("No row with id=" + id);
        }
    }

    @Override
    public List<Model> getAll() {
        Cursor cursor = new QueryBuilder()
                .select(getAllColumns())
                .from(getTableName())
                .execute(getRDb());
        if (cursor.getCount() > 0) {
            return mapToModels(cursor);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void save(Model model) {
        if (model.getId() == null) {
            new InsertBuilder()
                    .table(getTableName())
                    .execute(getWDb(), mapToValues(model));
        } else {
            new UpdateBuilder()
                    .table(getTableName())
                    .equals(AbstractModel.COLUMN_ID, model.getId().toString())
                    .execute(getWDb(), mapToValues(model));
        }
    }

    @Override
    public void delete(int id) {
        new DeleteBuilder().table(getTableName()).equals(AbstractModel.COLUMN_ID, id + "").execute(getWDb());
    }

    protected SQLiteDatabase getWDb() {
        return dbHelper.getWritableDatabase();
    }

    protected SQLiteDatabase getRDb() {
        return dbHelper.getReadableDatabase();
    }

    protected Model mapToModelAbstract(Model model, Cursor cursor) {
        model.setId(cursor.getInt(cursor.getColumnIndex(AbstractModel.COLUMN_ID)));
        model.setVersion(cursor.getLong(cursor.getColumnIndex(AbstractModel.COLUMN_VERSION)));
        return model;
    }

    protected ContentValues mapToValuesAbstract(Model model) {
        return mapToValuesAbstract(model, new ContentValues());
    }

    protected ContentValues mapToValuesAbstract(Model model, ContentValues values) {
        values.put(AbstractModel.COLUMN_ID, model.getId());
        values.put(AbstractModel.COLUMN_VERSION, model.getVersion());
        return values;
    }

    protected abstract String getTableName();

    protected abstract String[] getAllColumns();

    protected abstract Model mapToModel(Cursor cursor);

    protected abstract ContentValues mapToValues(Model model);

    protected List<Model> mapToModels(Cursor cursor) {
        List<Model> models = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            models.add(mapToModel(cursor));
        }
        return models;
    }

}
