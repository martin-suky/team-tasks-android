package cz.suky.teamtodo.android.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import cz.suky.teamtodo.android.R;
import cz.suky.teamtodo.android.adapter.TodoValueRow;
import cz.suky.teamtodo.android.annotation.InjectComponent;
import cz.suky.teamtodo.android.annotation.InjectService;
import cz.suky.teamtodo.android.model.Status;
import cz.suky.teamtodo.android.model.TodoList;
import cz.suky.teamtodo.android.model.TodoValue;
import cz.suky.teamtodo.android.service.TodoListService;

/**
 * Activity for displaying todo list.
 * Created by suky on 4.6.15.
 */
public class TodoListActivity extends AbstractActivity implements TodoValueRow.StatusChangeHandler {

    public static final String TODO_LIST_ID = "todoListId";

    @InjectComponent(R.id.tla_name)
    private TextView vName;
    @InjectComponent(R.id.tla_values)
    private ListView vValues;

    @InjectService
    private TodoListService todoListService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list_activity);

        loadAndDisplayTodoList(this.getIntent().getLongExtra(TODO_LIST_ID, 0));
    }

    private void loadAndDisplayTodoList(long listId) {
        TodoList todoList = todoListService.get(listId);
        vName.setText(todoList.getName());
        vValues.setAdapter(new TodoValueRow(this, this, todoList.getTodoValues()));
    }

    @Override
    public void onStatusChange(TodoValue value, Status newStatus) {
        todoListService.updateStatus(value.getId(), newStatus);
    }
}
