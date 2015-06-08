package cz.suky.teamtasks.android.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import cz.suky.teamtasks.android.R;
import cz.suky.teamtasks.android.adapter.TaskValueRow;
import cz.suky.teamtasks.android.annotation.InjectComponent;
import cz.suky.teamtasks.android.annotation.InjectService;
import cz.suky.teamtasks.android.model.Status;
import cz.suky.teamtasks.android.model.TaskValue;
import cz.suky.teamtasks.android.service.TaskListService;

/**
 * Activity for displaying task list.
 * Created by suky on 4.6.15.
 */
public class ViewTaskValuesActivity extends AbstractActivity implements TaskValueRow.StatusChangeHandler {

    public static final String TASK_LIST_ID = "taskListId";

    @InjectComponent(R.id.tla_name)
    private TextView vName;
    @InjectComponent(R.id.tla_values)
    private ListView vValues;

    @InjectService
    private TaskListService taskListService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_task_value_activity);

        loadAndDisplayTaskList(this.getIntent().getIntExtra(TASK_LIST_ID, 0));
    }

    private void loadAndDisplayTaskList(Integer listId) {
//        TaskList todoList = taskListService.get(listId);
//        vName.setText(todoList.getName());
//        vValues.setAdapter(new TaskValueRow(this, this, todoList.getTaskValues()));
    }

    @Override
    public void onStatusChange(TaskValue value, Status newStatus) {
//        taskListService.updateStatus(value.getId(), newStatus);
    }
}
