package cz.suky.teamtodo.android.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by suky on 3.6.15.
 */
public class Factory {

    public static final Factory instance = new Factory();

    private Map<Class<?>, Object> services = new HashMap<>();

    private final TodoListService todoListService;

    public Factory() {
        todoListService = new TodoListServiceImpl();
        services.put(TodoListService.class, todoListService);
    }

    public Object getObject(Class<?> clazz) {
        final Object o = services.get(clazz);
        if (null == o) {
            throw new IllegalArgumentException("No object found for class " + clazz);
        }
        return o;
    }
}
