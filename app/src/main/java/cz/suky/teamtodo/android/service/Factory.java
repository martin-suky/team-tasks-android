package cz.suky.teamtodo.android.service;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import cz.suky.teamtodo.android.activity.MainActivity;

/**
 * Created by suky on 3.6.15.
 */
public class Factory {

    private static Factory instance;
    private final  Context context;

    private Map<Class<?>, Object> services = new HashMap<>();

    private final TodoListService todoListService;

    public Factory(Context context) {
        this.context = context;
        todoListService = new TodoListServiceImpl();
        services.put(TodoListService.class, todoListService);
    }

    public static Factory get() {
        if (instance == null) {
            instance = new Factory(MainActivity.context);
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
