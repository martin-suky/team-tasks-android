package cz.suky.teamtodo.android.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import cz.suky.teamtodo.android.R;
import cz.suky.teamtodo.android.model.Status;
import cz.suky.teamtodo.android.model.TodoValue;

/**
 * One task from todo list.
 * Created by suky on 4.6.15.
 */
public class TodoValueRow extends AbstractArrayAdapter<TodoValue> {

    private final StatusChangeHandler handler;

    public interface StatusChangeHandler {
        void onStatusChange(TodoValue value, Status newStatus);
    }

    public TodoValueRow(StatusChangeHandler handler, Context context, List<TodoValue> objects) {
        super(context, objects);
        this.handler = handler;
    }

    @Override
    protected int getViewId() {
        return R.layout.todo_value_row;
    }

    @Override
    protected void fillView(View view, final TodoValue object) {
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
                color = R.color.todo_done;
                break;
            case OPEN:
            default:
                color = R.color.todo_open;
                break;
        }

        return getContext().getResources().getColor(color);
    }
}
