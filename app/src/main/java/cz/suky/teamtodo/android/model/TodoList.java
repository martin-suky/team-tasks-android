package cz.suky.teamtodo.android.model;

/**
 * Created by suky on 2.6.15.
 */
public class TodoList extends AbstractModel {
    private static final long serialVersionUID = 1;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
