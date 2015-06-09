package cz.suky.teamtasks.android.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by suky on 7.6.15.
 */
public abstract class AsyncService<Request, Response> extends AsyncTask<Request, Void, Response> {

    private Context context;
    private ServiceResultCallback<Response> callback;
    private ProgressDialog loading;

    public AsyncService(Context context, ServiceResultCallback<Response> callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loading = ProgressDialog.show(context, null, "Loading");
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
        if (loading != null) {
            loading.dismiss();
            loading = null;
        }
        callback.processResult(response);
    }
}
