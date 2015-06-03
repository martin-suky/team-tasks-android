package cz.suky.teamtodo.android.model;

/**
 * Created by suky on 2.6.15.
 */
public class TodoValue extends AbstractModel {
    private String text;
    private Status status;
    private TodoList parent;

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

    public TodoList getParent() {
        return parent;
    }

    public void setParent(TodoList parent) {
        this.parent = parent;
    }
}
