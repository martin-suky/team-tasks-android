package cz.suky.teamtasks.android.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import cz.suky.teamtasks.android.R;
import cz.suky.teamtasks.android.model.Status;
import cz.suky.teamtasks.android.model.TaskValue;

/**
 * One task from todo list.
 * Created by suky on 4.6.15.
 */
public class TaskValueRow extends AbstractArrayAdapter<TaskValue> {

    private final StatusChangeHandler handler;

    public interface StatusChangeHandler {
        void onStatusChange(TaskValue value, Status newStatus);
    }

    public TaskValueRow(StatusChangeHandler handler, Context context, List<TaskValue> objects) {
        super(context, objects);
        this.handler = handler;
    }

    @Override
    protected int getViewId() {
        return R.layout.task_value_row;
    }

    @Override
    protected void fillView(View view, final TaskValue object) {
        CheckBox vStatus = findViewByIdTyped(view, R.id.tvr_status);
        TextView vName = findViewByIdTyped(view, R.id.tvr_name);

        vName.setText(object.getText());
        vName.setTextColor(getColorFromStatus(object.getStatus()));
        vStatus.setChecked(Status.DONE.equals(object.getStatus()));
        vStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                handler.onStatusChange(object, b ? Status.DONE : Status.OPEN);
            }
        });
    }

    private int getColorFromStatus(Status status) {
        int color;
        switch (status) {
            case DONE:
                color = R.color.task_done;
                break;
            case OPEN:
            default:
                color = R.color.task_open;
                break;
        }

        return getContext().getResources().getColor(color);
    }
}
