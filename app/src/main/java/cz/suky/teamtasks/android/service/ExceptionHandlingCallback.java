package cz.suky.teamtasks.android.service;

import android.content.Context;
import android.widget.Toast;

import cz.suky.teamtasks.android.R;

/**
 * Created by suky on 16.6.15.
 */
public abstract class ExceptionHandlingCallback<Payload> implements ServiceResultCallback<Payload> {

    private final Context context;

    protected ExceptionHandlingCallback(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void processResult(Response<Payload> result) {
        if (result.status == Response.ServiceStatus.OK) {
            processPayload(result.payload);
        } else {
            handleException(result);
        }
    }

    protected void handleException(Response<Payload> result) {
        Toast.makeText(context, R.string.service_error, Toast.LENGTH_LONG).show();
    }

    protected abstract void processPayload(Payload payload);
}
