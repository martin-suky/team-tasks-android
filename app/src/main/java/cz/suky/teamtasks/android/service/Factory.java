package cz.suky.teamtasks.android.service;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import cz.suky.teamtasks.android.activity.ViewTaskListActivity;
import cz.suky.teamtasks.android.db.TaskValueDao;
import cz.suky.teamtasks.android.db.TaskValueDaoImpl;
import cz.suky.teamtasks.android.db.TeamTasksDbHelper;
import cz.suky.teamtasks.android.db.TaskListDao;
import cz.suky.teamtasks.android.db.TaskListDaoImpl;

/**
 * Created by suky on 3.6.15.
 */
public class Factory {

    private static Factory instance;
    private final  Context context;

    private Map<Class<?>, Object> services = new HashMap<>();

    private final TeamTasksDbHelper dbHelper;
    private final TaskListService   taskListService;
    private final TaskValueService  taskValueService;
    private final TaskListDao       taskListDao;
    private final TaskValueDao      taskValueDao;

    public Factory(Context context) {
        this.context = context;
        dbHelper = new TeamTasksDbHelper(context);
        taskListDao = new TaskListDaoImpl(dbHelper);
        taskValueDao = new TaskValueDaoImpl(dbHelper);
        taskListService = new TaskListServiceImpl(context, taskListDao);
        taskValueService = new TaskValueServiceImpl(context, taskValueDao);

        services.put(TaskListService.class, taskListService);
        services.put(TaskValueService.class, taskValueService);
    }

    public static Factory get() {
        if (instance == null) {
            instance = new Factory(ViewTaskListActivity.context);
        }
        return instance;
    }

    public Object getObject(Class<?> clazz) {
        final Object o = services.get(clazz);
        if (null == o) {
            throw new IllegalArgumentException("No object found for class " + clazz);
        }
        return o;
    }
}
