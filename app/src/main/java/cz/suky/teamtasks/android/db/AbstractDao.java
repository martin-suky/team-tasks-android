package cz.suky.teamtasks.android.db;

import java.util.List;

import cz.suky.teamtasks.android.model.AbstractModel;

/**
 * Created by suky on 6.6.15.
 */
public interface AbstractDao<Model extends AbstractModel> {
    Model getById(int id);

    List<Model> getAll();

    void save(Model model);

    void delete(int id);
}
