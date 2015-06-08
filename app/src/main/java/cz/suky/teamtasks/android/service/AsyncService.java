package cz.suky.teamtasks.android.service;

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
            if (requests != null && requests.length > 0) {
                return doIt(requests[0]);
            } else {
                return doIt(null);
            }
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
