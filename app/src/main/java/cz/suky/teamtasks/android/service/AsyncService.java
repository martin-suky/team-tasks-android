package cz.suky.teamtasks.android.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by suky on 7.6.15.
 */
public abstract class AsyncService<Payload> extends AsyncTask<Void, Void, Response<Payload>> {

    private Context context;
    private ServiceResultCallback<Payload> callback;
    private ProgressDialog loading;

    public AsyncService(Context context, ServiceResultCallback<Payload> callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loading = ProgressDialog.show(context, null, "Loading");
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
