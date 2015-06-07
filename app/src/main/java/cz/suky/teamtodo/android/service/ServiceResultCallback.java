package cz.suky.teamtodo.android.service;

/**
 * Created by suky on 7.6.15.
 */
public interface ServiceResultCallback<Response> {
    void processResult(Response result);
}
