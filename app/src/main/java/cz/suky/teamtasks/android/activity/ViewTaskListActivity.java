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
import cz.suky.teamtasks.android.adapter.TaskListRow;
import cz.suky.teamtasks.android.annotation.InjectComponent;
import cz.suky.teamtasks.android.annotation.InjectService;
import cz.suky.teamtasks.android.model.TaskList;
import cz.suky.teamtasks.android.service.ExceptionHandlingCallback;
import cz.suky.teamtasks.android.service.Response;
import cz.suky.teamtasks.android.service.ServiceResultCallback;
import cz.suky.teamtasks.android.service.TaskListService;
import cz.suky.teamtasks.android.service.TaskValueService;
import cz.suky.teamtasks.android.util.Constants;

import static android.widget.AdapterView.OnItemClickListener;


public class ViewTaskListActivity extends AbstractActivity {

    public static Context context;

    private List<TaskList> taskLists;

    @InjectService
    private TaskListService taskListService;

    @InjectService
    private TaskValueService taskValueService;

    @InjectComponent(R.id.vtla_tasks)
    private ListView tasks;

    private static final int CONTEXT_EDIT = 1;
    private static final int CONTEXT_DELETE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ViewTaskListActivity.context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_task_list_activity);

        tasks.setOnItemClickListener(taskListRowClickHandler);
        registerForContextMenu(tasks);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_task_list, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.vtla_tasks) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle(getResources().getString(R.string.vtla_context_menu_header));
            menu.add(Menu.NONE, CONTEXT_EDIT, 0, R.string.vtla_context_menu_edit);
            menu.add(Menu.NONE, CONTEXT_DELETE, 1, R.string.vtla_context_menu_delete);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = menuInfo.position;
        switch (item.getItemId()) {
            case CONTEXT_EDIT:
                editTaskListOnPosition(position);
                break;
            case CONTEXT_DELETE:
                deleteTaskListOnPosition(position);
                break;
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.mvtl_add_task_list:
                startActivityForTask(EditTaskListActivity.class, null);
                return true;
            case R.id.mvtl_settings:
            default:
                return true;
        }
    }

    private void editTaskListOnPosition(int position) {
        if (taskLists != null) {
            TaskList taskList = taskLists.get(position);
            startActivityForTask(EditTaskListActivity.class, taskList);
        }
    }

    private void deleteTaskListOnPosition(int position) {
        if (taskLists != null) {
            TaskList taskList = taskLists.get(position);
            Integer taskToDelete = taskList.getId();
            taskValueService.getCountOfTaskValues(taskToDelete, new DeleteTaskListServiceResulCallBack(this, taskToDelete));
        }
    }

    private void startActivityForTask(Class<? extends Activity> clazz, TaskList taskList) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(Constants.TASK_LIST_ID, taskList != null ? taskList.getId() : 0);
        startActivity(intent);
    }

    private String formatString(int stringId, Object... parameters) {
        String string = getResources().getString(stringId);
        if (parameters.length > 0) {
            return String.format(string, parameters);
        } else {
            return string;
        }
    }

    private void refreshData() {
        taskListService.getAll(getAllCallback);
    }

    private OnItemClickListener taskListRowClickHandler = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            TaskList selectedTask = (TaskList) adapterView.getItemAtPosition(i);
            startActivityForTask(ViewTaskValuesActivity.class, selectedTask);
        }
    };

    private ServiceResultCallback<List<TaskList>> getAllCallback = new ExceptionHandlingCallback<List<TaskList>>(this) {
        @Override
        protected void processPayload(List<TaskList> taskLists) {
            ViewTaskListActivity.this.taskLists = taskLists;
            TaskListRow taskListRow = new TaskListRow(ViewTaskListActivity.this, taskLists);
            tasks.setAdapter(taskListRow);
        }
    };

    private class DeleteTaskListServiceResulCallBack extends ExceptionHandlingCallback<Integer> {

        private final Integer taskToDelete;

        protected DeleteTaskListServiceResulCallBack(Context context, Integer taskToDelete) {
            super(context);
            this.taskToDelete = taskToDelete;
        }

        @Override
        protected void processPayload(Integer integer) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ViewTaskListActivity.this);
            builder.setMessage(formatString(R.string.vtla_confirm_delete, integer))
                    .setPositiveButton(formatString(R.string.button_yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            taskListService.delete(taskToDelete, new ExceptionHandlingCallback<Void>(ViewTaskListActivity.this) {
                                @Override
                                protected void processPayload(Void aVoid) {
                                    refreshData();
                                }
                            });
                        }
                    })
                    .setNegativeButton(formatString(R.string.button_no), null).create().show();
        }
    }

    ;
}
