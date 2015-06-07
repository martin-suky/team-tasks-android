package cz.suky.teamtodo.android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import cz.suky.teamtodo.android.R;
import cz.suky.teamtodo.android.adapter.TodoListRow;
import cz.suky.teamtodo.android.annotation.InjectComponent;
import cz.suky.teamtodo.android.annotation.InjectService;
import cz.suky.teamtodo.android.model.TodoList;
import cz.suky.teamtodo.android.service.ServiceResultCallback;
import cz.suky.teamtodo.android.service.TodoListService;

import static android.widget.AdapterView.OnItemClickListener;


public class MainActivity extends AbstractActivity {

    public static Context context;

    @InjectService
    private TodoListService todoListService;

    @InjectComponent(R.id.ma_todos)
    private ListView todos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainActivity.context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        todoListService.getAll(new GetAllCallback());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void selectTodo(TodoList todoList) {
        Intent intent = new Intent(this, TodoListActivity.class);
        intent.putExtra(TodoListActivity.TODO_LIST_ID, todoList.getId());

        startActivity(intent);
    }

    private OnItemClickListener todoListRowClickHandler = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            TodoList selectedTodo = (TodoList) adapterView.getItemAtPosition(i);
            selectTodo(selectedTodo);
        }
    };

    private class GetAllCallback implements ServiceResultCallback<List<TodoList>> {

        @Override
        public void processResult(List<TodoList> result) {
            TodoListRow todoListRow = new TodoListRow(MainActivity.this, result);
            todos.setAdapter(todoListRow);
            todos.setOnItemClickListener(todoListRowClickHandler);
        }
    }
}
