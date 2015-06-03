package cz.suky.teamtodo.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cz.suky.teamtodo.android.R;
import cz.suky.teamtodo.android.model.TodoList;

/**
 * Created by suky on 3.6.15.
 */
public class TodoListRow extends AbstractArrayAdapter<TodoList> {

    public TodoListRow(Context context, List<TodoList> objects) {
        super(context, objects);
    }

    @Override
    protected int getViewId() {
        return R.layout.todo_list_row;
    }

    @Override
    protected void fillView(View view, TodoList object) {
        TextView todoListName = findViewByIdTyped(view, R.id.tlr_name);
        todoListName.setText(object.getName());
    }
}
