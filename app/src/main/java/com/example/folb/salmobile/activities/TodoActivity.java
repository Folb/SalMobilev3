package com.example.folb.salmobile.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.folb.salmobile.R;
import com.example.folb.salmobile.activities.lousecounting.LouseCountingActivity;
import com.example.folb.salmobile.adapters.TodoListAdapter;
import com.example.folb.salmobile.models.ATask;
import com.example.folb.salmobile.models.LouseCounting;
import com.google.gson.Gson;

import java.util.ArrayList;

public class TodoActivity extends Activity implements AdapterView.OnItemClickListener {

    public final String TAG = TodoActivity.class.toString();

    private ListView todoList;
    private TodoListAdapter adapter;

    private ArrayList<ATask> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        tasks = new ArrayList<>();
        if (getIntent().hasExtra("countings")) {
            getTasks();
        }

        todoList = (ListView) findViewById(R.id.list_todo);
        adapter = new TodoListAdapter(this, R.layout.todo_list_item, tasks);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        if (tasks.get(position) instanceof LouseCounting) {
            intent.setClass(this, LouseCountingActivity.class);
            intent.putExtra("counting", new Gson().toJson(tasks.get(position)));
        }
        startActivityForResult(intent, 1);
    }

    private void getTasks() {
        Intent i = getIntent();
        tasks = (ArrayList<ATask>) i.getSerializableExtra("countings");
    }

    @Override
    protected void onResume() {
        super.onResume();

        todoList.setAdapter(adapter);
        todoList.setClickable(true);
        todoList.setOnItemClickListener(this);
    }
}
