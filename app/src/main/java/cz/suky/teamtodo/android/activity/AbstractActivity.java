package cz.suky.teamtodo.android.activity;

import android.app.Activity;

import java.lang.reflect.Field;

import cz.suky.teamtodo.android.annotation.Inject;

/**
 * Created by suky on 2.6.15.
 */
public class AbstractActivity extends Activity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        injectComponents();
    }

    private void injectComponents() {
        Field[] declaredFields = getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Inject.class)) {
                injectField(field);
            }
        }
    }

    private void injectField(Field field) {
        Inject annotation = field.getAnnotation(Inject.class);
        try {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            field.set(this, findViewById(annotation.value()));
            field.setAccessible(accessible);
        } catch (Exception e) {
            throw new RuntimeException("Injection failed! Cannot run!", e);
        }
    }
}
