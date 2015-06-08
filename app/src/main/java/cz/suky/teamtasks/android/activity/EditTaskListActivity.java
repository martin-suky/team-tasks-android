package cz.suky.teamtasks.android.activity;

import android.os.Bundle;

import cz.suky.teamtasks.android.R;

/**
 * Created by suky on 8.6.15.
 */
public class EditTaskListActivity extends AbstractActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_task_list_activity);
        setTitle(getResources().getString(R.string.edit_task_value));
    }
}
