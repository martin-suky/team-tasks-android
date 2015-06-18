package cz.suky.teamtasks.android.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import cz.suky.teamtasks.android.R;
import cz.suky.teamtasks.android.adapter.TaskValueRow;
import cz.suky.teamtasks.android.annotation.InjectComponent;
import cz.suky.teamtasks.android.annotation.InjectService;
import cz.suky.teamtasks.android.model.Status;
import cz.suky.teamtasks.android.model.TaskList;
import cz.suky.teamtasks.android.model.TaskValue;
import cz.suky.teamtasks.android.service.ExceptionHandlingCallback;
import cz.suky.teamtasks.android.service.TaskListService;
import cz.suky.teamtasks.android.service.TaskValueService;
import cz.suky.teamtasks.android.util.Constants;

/**
 * Activity for displaying task list.
 * Created by suky on 4.6.15.
 */
public class ViewTaskValuesActivity extends AbstractActivity implements TaskValueRow.StatusChangeHandler {

    private int intExtra;

    private static final int CONTEXT_EDIT   = 1;
    private static final int CONTEXT_DELETE = 2;

    private List<TaskValue> taskValues;

    @InjectComponent(R.id.tla_values)
    private ListView vValues;

    @InjectService
    private TaskListService taskListService;

    @InjectService
    private TaskValueService taskValueService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_task_value_activity);

        registerForContextMenu(vValues);
    }

    @Override
    protected void onResume() {
        super.onResume();
        intExtra = this.getIntent().getIntExtra(Constants.TASK_LIST_ID, 0);
        loadAndDisplayTaskList(intExtra);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.tla_values) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle(getResources().getString(R.string.vtva_context_menu_header));
            menu.add(Menu.NONE, CONTEXT_EDIT, 0, R.string.vtva_context_menu_edit);
            menu.add(Menu.NONE, CONTEXT_DELETE, 1, R.string.vtva_context_menu_delete);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = menuInfo.position;
        switch (item.getItemId()) {
            case CONTEXT_EDIT:
                editTaskValueOnPosition(position);
                break;
            case CONTEXT_DELETE:
                deleteTaskListOnPosition(position);
                break;
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_task_value, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.mvtv_add_value:
                startActivityForTaskValue(EditTaskValueActivity.class, null);
                return true;
            default:
                return true;
        }
    }

    private void editTaskValueOnPosition(int position) {
        TaskValue taskValue = this.taskValues.get(position);
        startActivityForTaskValue(EditTaskValueActivity.class, taskValue);
    }

    private void deleteTaskListOnPosition(int position) {
        final TaskValue taskValue = this.taskValues.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.vtva_confirm_delete))
                .setPositiveButton(getString(R.string.button_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        taskValueService.delete(taskValue.getId(), new ExceptionHandlingCallback<Void>(ViewTaskValuesActivity.this) {
                            @Override
                            protected void processPayload(Void aVoid) {
                                onResume();
                            }
                        });
                    }
                })
                .setNegativeButton(getString(R.string.button_no), null).create().show();
    }

    private void loadAndDisplayTaskList(Integer listId) {
        taskListService.get(listId, new GetTaskListServiceCallback(this));
        taskValueService.getAllForTaskListId(listId, new GetValuesServiceCallback(this));
    }

    @Override
    public void onStatusChange(final TaskValue value, final Status newStatus) {
        taskValueService.setStatus(value.getId(), newStatus, new ExceptionHandlingCallback<Void>(this) {
            @Override
            protected void processPayload(Void aVoid) {
            }
        });
    }

    private void startActivityForTaskValue(Class<? extends Activity> clazz, TaskValue taskValue) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(Constants.TASK_VALUE_ID, taskValue != null ? taskValue.getId() : 0);
        intent.putExtra(Constants.TASK_LIST_ID, taskValue != null ? taskValue.getTaskListId() : intExtra);
        startActivity(intent);
    }

    private class GetTaskListServiceCallback extends ExceptionHandlingCallback<TaskList> {

        protected GetTaskListServiceCallback(Context context) {
            super(context);
        }
        @Override
        protected void processPayload(TaskList taskList) {
            setTitle(taskList.getName());
        }

    }

    private class GetValuesServiceCallback extends ExceptionHandlingCallback<List<TaskValue>> {

        protected GetValuesServiceCallback(Context context) {
            super(context);
        }

        @Override
        protected void processPayload(List<TaskValue> taskValues) {
            ViewTaskValuesActivity.this.taskValues = taskValues;
            TaskValueRow taskValueRow = new TaskValueRow(ViewTaskValuesActivity.this, ViewTaskValuesActivity.this, taskValues);
            vValues.setAdapter(taskValueRow);
        }
    }
}
