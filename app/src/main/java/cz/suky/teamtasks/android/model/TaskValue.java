package cz.suky.teamtasks.android.model;

/**
 * Object for one row of task list
 * Created by suky on 2.6.15.
 */
public class TaskValue extends AbstractModel {

    public static final String TABLE_NAME = "TaskValue";
    public static final String COLUMN_TEXT = "text";
    public static final String COLUMN_LIST_ID = "taskListId";

    private String text;
    private Status status;
    private Integer taskListId;

    public TaskValue() {
    }

    public TaskValue(String text, Status status) {
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

    public Integer getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(Integer taskListId) {
        this.taskListId = taskListId;
    }
}
