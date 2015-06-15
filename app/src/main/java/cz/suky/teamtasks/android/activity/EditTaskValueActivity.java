package cz.suky.teamtasks.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cz.suky.teamtasks.android.R;
import cz.suky.teamtasks.android.annotation.InjectComponent;
import cz.suky.teamtasks.android.annotation.InjectService;
import cz.suky.teamtasks.android.model.Status;
import cz.suky.teamtasks.android.model.TaskList;
import cz.suky.teamtasks.android.model.TaskValue;
import cz.suky.teamtasks.android.service.Response;
import cz.suky.teamtasks.android.service.ServiceResultCallback;
import cz.suky.teamtasks.android.service.TaskValueService;
import cz.suky.teamtasks.android.util.Constants;

/**
 * Created by suky on 8.6.15.
 */
public class EditTaskValueActivity extends AbstractActivity {

    @InjectComponent(R.id.etva_text)
    private EditText name;

    @InjectComponent(R.id.etva_submit)
    private Button submit;

    @InjectService
    private TaskValueService taskValueService;

    private TaskValue taskValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_task_value_activity);

        int taskListId = getIntent().getIntExtra(Constants.TASK_LIST_ID, 0);
        int taskValueId = getIntent().getIntExtra(Constants.TASK_VALUE_ID, 0);

        setTitle(getTitleForId(taskValueId));
        loadTaskList(taskListId, taskValueId);
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
        taskValue.setText(name.getText().toString());
        taskValueService.save(taskValue, new ServiceResultCallback<Void>() {
            @Override
            public void processResult(Response<Void> result) {
                finish();
            }
        });
    }

    private void loadTaskList(int taskListId, int taskValueId) {
        if (taskValueId == 0) {
            TaskValue taskValue = new TaskValue();
            taskValue.setTaskListId(taskListId);
            taskValue.setStatus(Status.OPEN);
            setTaskListToForm(taskValue);
        } else {
            taskValueService.get(taskValueId, new ServiceResultCallback<TaskValue>() {
                @Override
                public void processResult(Response<TaskValue> result) {
                    setTaskListToForm(result.payload);
                }
            });
        }
    }

    private String getTitleForId(int taskListId) {
        int key;
        if (taskListId == 0) {
            key = R.string.etva_title_add;
        } else {
            key = R.string.etva_title_edit;

        }
        return getResources().getString(key);
    }

    private void setTaskListToForm(TaskValue taskValue) {
        this.taskValue = taskValue;
        name.setText(taskValue.getText());
    }
}
