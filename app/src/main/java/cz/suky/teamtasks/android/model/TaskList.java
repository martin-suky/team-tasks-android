package cz.suky.teamtasks.android.model;

import java.util.List;

/**
 * TTodo list object
 * Created by suky on 2.6.15.
 */
public class TaskList extends AbstractModel {

    public static final String TABLE_NAME  = "TaskList";
    public static final String COLUMN_NAME = "name";

    public static final TaskList notFoundList = new TaskList("Nothing here...");

    private static final long serialVersionUID = 1;

    private String name;

    private List<TaskValue> taskValues;

    public TaskList() {

    }

    public TaskList(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TaskValue> getTaskValues() {
        return taskValues;
    }

    public void setTaskValues(List<TaskValue> taskValues) {
        this.taskValues = taskValues;
    }
}
