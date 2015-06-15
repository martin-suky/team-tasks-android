package cz.suky.teamtasks.android.service;

import java.util.ResourceBundle;

/**
 * Created by msoukup on 6/15/2015.
 */
public class Response<Payload> {
    public ServiceStatus status;
    public Payload payload;
    public String errorString;
    public Exception e;

    public static Response<Void> ok() {
        Response<Void> ok = new Response<>();
        ok.status = ServiceStatus.OK;
        return ok;
    }

    public static <Payload> Response<Payload> ok(Payload payload) {
        Response<Payload> ok = new Response<>();
        ok.status = ServiceStatus.OK;
        ok.payload = payload;
        return ok;
    }

    public static <Payload> Response<Payload> error(String errorString, Exception e) {
        Response<Payload> error = new Response<>();
        error.status = ServiceStatus.ERROR;
        error.errorString = errorString;
        error.e = e;
        return error;
    }

    private Response() {
    }

    public enum ServiceStatus {
        OK, ERROR
    }
}
