package cz.suky.teamtasks.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cz.suky.teamtasks.android.R;
import cz.suky.teamtasks.android.annotation.InjectComponent;
import cz.suky.teamtasks.android.annotation.InjectService;
import cz.suky.teamtasks.android.model.TaskList;
import cz.suky.teamtasks.android.service.ServiceResultCallback;
import cz.suky.teamtasks.android.service.TaskListService;
import cz.suky.teamtasks.android.util.Constants;

/**
 * Created by suky on 8.6.15.
 */
public class EditTaskListActivity extends AbstractActivity {

    @InjectComponent(R.id.etla_name)
    private EditText name;

    @InjectComponent(R.id.etla_submit)
    private Button submit;

    @InjectService
    private TaskListService taskListService;

    private TaskList taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_task_list_activity);

        int taskListId = getIntent().getIntExtra(Constants.TASK_LIST_ID, 0);

        setTitle(getTitleForId(taskListId));
        loadTaskList(taskListId);
        setSubmitHandler();
    }

    private void setSubmitHandler() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bindTaskListAndStore();
            }
        });
    }

    private void bindTaskListAndStore() {
        taskList.setName(name.getText().toString());
        taskListService.save(taskList, new ServiceResultCallback<Void>() {
            @Override
            public void processResult(Void result) {
                finish();
            }
        });
    }

    private void loadTaskList(int taskListId) {
        if (taskListId == 0) {
            setTaskListToForm(new TaskList());
        } else {
            taskListService.get(taskListId, new ServiceResultCallback<TaskList>() {
                @Override
                public void processResult(TaskList result) {
                    setTaskListToForm(result);
                }
            });
        }
    }

    private String getTitleForId(int taskListId) {
        int key;
        if (taskListId == 0) {
            key = R.string.etla_title_add;
        } else {
            key = R.string.etla_title_edit;

        }
        return getResources().getString(key);
    }

    private void setTaskListToForm(TaskList taskList) {
        this.taskList = taskList;
        name.setText(taskList.getName());
    }
}
