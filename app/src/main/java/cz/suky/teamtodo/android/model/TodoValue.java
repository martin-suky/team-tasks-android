package cz.suky.teamtodo.android.model;

/**
 * Object for one row of ToDo list
 * Created by suky on 2.6.15.
 */
public class TodoValue extends AbstractModel {

    public static final String TABLE_TODO_VALUE = "TodoValue";
    public static final String COLUMN_TEXT      = "text";

    private String text;
    private Status status;

    public TodoValue() {
    }

    public TodoValue(String text, Status status) {
        this.text = text;
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
