package cz.suky.teamtasks.android.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import cz.suky.teamtasks.android.R;

/**
 * Created by suky on 7.6.15.
 */
public abstract class AsyncService<Payload> extends AsyncTask<Void, Void, Response<Payload>> {

    private ServiceResultCallback<Payload> callback;
    private ProgressDialog                 loading;

    public AsyncService(ServiceResultCallback<Payload> callback) {
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (callback instanceof ExceptionHandlingCallback) {
            Context context = ((ExceptionHandlingCallback) callback).getContext();
            loading = ProgressDialog.show(context, null, context.getResources().getString(R.string.service_loading));
        }
    }

    @Override
    protected Response doInBackground(Void... requests) {
        try {
            return doIt();
        } catch (Exception e) {
            return Response.error("Something bad happened.", e);
        }
    }

    protected abstract Response doIt();

    @Override
    protected void onPostExecute(Response response) {
        if (loading != null) {
            loading.dismiss();
            loading = null;
        }
        callback.processResult(response);
    }
}
