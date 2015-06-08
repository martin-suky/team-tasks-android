package cz.suky.teamtasks.android.activity;

import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.Field;

import cz.suky.teamtasks.android.annotation.InjectComponent;
import cz.suky.teamtasks.android.annotation.InjectService;
import cz.suky.teamtasks.android.service.Factory;

/**
 * Implementation of basic functionality for Activities
 * Created by suky on 2.6.15.
 */
public abstract class AbstractActivity extends AppCompatActivity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        inject();
    }

    private void inject() {
        Field[] declaredFields = getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(InjectComponent.class)) {
                injectComponents(field);
            } else if (field.isAnnotationPresent(InjectService.class)) {
                injectService(field);
            }
        }
    }

    private void injectService(Field field) {
        InjectService annotation = field.getAnnotation(InjectService.class);
        Class<?> type = field.getType();
        Object object = Factory.get().getObject(type);
        setToField(field, object);
    }

    private void injectComponents(Field field) {
        InjectComponent annotation = field.getAnnotation(InjectComponent.class);
        setToField(field, findViewById(annotation.value()));
    }

    private void setToField(Field field, Object value) {
        try {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            field.set(this, value);
            field.setAccessible(accessible);
        } catch (Exception e) {
            throw new RuntimeException("Injection failed! Cannot run!", e);
        }
    }
}
