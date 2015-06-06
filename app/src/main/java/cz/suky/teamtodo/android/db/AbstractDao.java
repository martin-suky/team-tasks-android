package cz.suky.teamtodo.android.db;

import java.util.List;

import cz.suky.teamtodo.android.model.AbstractModel;

/**
 * Created by suky on 6.6.15.
 */
public interface AbstractDao<Model extends AbstractModel> {
    Model getById(long id);

    List<Model> getAll();

    void save(Model model);

    void delete(Model model);
}
