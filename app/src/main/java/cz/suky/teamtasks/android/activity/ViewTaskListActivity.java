package cz.suky.teamtasks.android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import cz.suky.teamtasks.android.R;
import cz.suky.teamtasks.android.adapter.TaskListRow;
import cz.suky.teamtasks.android.annotation.InjectComponent;
import cz.suky.teamtasks.android.annotation.InjectService;
import cz.suky.teamtasks.android.model.TaskList;
import cz.suky.teamtasks.android.service.ServiceResultCallback;
import cz.suky.teamtasks.android.service.TaskListService;
import cz.suky.teamtasks.android.util.Constants;

import static android.widget.AdapterView.OnItemClickListener;


public class ViewTaskListActivity extends AbstractActivity {

    public static Context context;

    @InjectService
    private TaskListService taskListService;

    @InjectComponent(R.id.ma_todos)
    private ListView todos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ViewTaskListActivity.context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_task_list_activity);

        taskListService.getAll(new GetAllCallback());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_add_task_list:
                startActivity(new Intent(this, EditTaskListActivity.class));
                return true;
            case R.id.action_settings:
            default:
                return true;
        }
    }

    private void selectTodo(TaskList taskList) {
        Intent intent = new Intent(this, ViewTaskValuesActivity.class);
        intent.putExtra(Constants.TASK_LIST_ID, taskList.getId());

        startActivity(intent);
    }

    private OnItemClickListener todoListRowClickHandler = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            TaskList selectedTodo = (TaskList) adapterView.getItemAtPosition(i);
            selectTodo(selectedTodo);
        }
    };

    private class GetAllCallback implements ServiceResultCallback<List<TaskList>> {

        @Override
        public void processResult(List<TaskList> result) {
            TaskListRow taskListRow = new TaskListRow(ViewTaskListActivity.this, result);
            todos.setAdapter(taskListRow);
            todos.setOnItemClickListener(todoListRowClickHandler);
        }
    }
}
