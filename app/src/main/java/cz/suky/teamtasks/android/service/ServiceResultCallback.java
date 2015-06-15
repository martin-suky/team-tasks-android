package cz.suky.teamtasks.android.service;

/**
 * Created by suky on 7.6.15.
 */
public interface ServiceResultCallback<Payload> {
    void processResult(Response<Payload> result);
}
