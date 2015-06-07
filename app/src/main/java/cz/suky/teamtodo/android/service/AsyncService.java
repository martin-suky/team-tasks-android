package cz.suky.teamtodo.android.service;

import android.os.AsyncTask;

/**
 * Created by suky on 7.6.15.
 */
public abstract class AsyncService<Request, Response> extends AsyncTask<Request, Void, Response> {

    private ServiceResultCallback<Response> callback;

    public AsyncService(ServiceResultCallback<Response> callback) {
        this.callback = callback;
    }

    @Override
    protected Response doInBackground(Request... requests) {
        try {
            return doIt(requests[0]);
        } catch (Exception e) {
            throw new RuntimeException("Something bad happened.", e);
        }
    }

    protected abstract Response doIt(Request request);

    @Override
    protected void onPostExecute(Response response) {
        callback.processResult(response);
    }
}
