package com.example.android_development_assignment_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity {

    TextView tv_count_completed_task, tv_count_task, tv_list_name;
    ListView lv_tasks;
    ArrayList<TaskItem> al_items;
    private ListView lv_task;
    private CustomAdapter customAdapter;
    private int taskCount;
    private static int completed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        tv_count_completed_task = (TextView) findViewById(R.id.tv_count_completed_task);
        tv_count_task = (TextView) findViewById(R.id.tv_count_task);
        tv_list_name = (TextView) findViewById(R.id.tv_list_name);
        lv_tasks = (ListView) findViewById(R.id.lv_tasks);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.task_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_add_task:
                //Go to Task Dialog
                Toast.makeText(this, "New Task Dialog Opened...", Toast.LENGTH_SHORT);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }
}
