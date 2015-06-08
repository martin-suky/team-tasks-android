package cz.suky.teamtasks.android.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import cz.suky.teamtasks.android.R;
import cz.suky.teamtasks.android.model.TaskList;

/**
 * Created by suky on 3.6.15.
 */
public class TaskListRow extends AbstractArrayAdapter<TaskList> {

    public TaskListRow(Context context, List<TaskList> objects) {
        super(context, objects);
    }

    @Override
    protected int getViewId() {
        return R.layout.task_list_row;
    }

    @Override
    protected void fillView(View view, TaskList object) {
        TextView taskListName = findViewByIdTyped(view, R.id.tlr_name);
        taskListName.setText(object.getName());
    }
}
