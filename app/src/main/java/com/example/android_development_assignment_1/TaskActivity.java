package com.example.android_development_assignment_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity {

    TextView tv_count_completed_task, tv_count_task, tv_list_name;
    ListView lv_tasks;
    ArrayList<TaskItem> al_items;
    private String listName;
    private String taskName;
    private String taskDate;
    private ListView lv_task;
    private CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        tv_count_completed_task = (TextView) findViewById(R.id.tv_count_completed_task);
        tv_count_task = (TextView) findViewById(R.id.tv_count_task);
        tv_list_name = (TextView) findViewById(R.id.tv_list_name);
        lv_tasks = (ListView) findViewById(R.id.lv_tasks);


        al_items = new ArrayList<TaskItem>();

        customAdapter = new CustomAdapter(this, al_items);

        lv_tasks.setAdapter(customAdapter);


        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Tasks", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS tasks (list_name VARCHAR,task_name VARCHAR,due_date VARCHAR,checked BOOLEAN," +
                    "FOREIGN KEY(list_name) REFERENCES Lists(list_name), PRIMARY KEY(list_name,task_name) )");

        } catch (Exception e) {
            System.out.println("Exception occured!");
        }


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
                item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent = new Intent(TaskActivity.this, CustomTaskActivity.class);
                        //intent.putExtra("List Name", lv_mainlist.toString());
                        startActivity(intent);

                        return false;
                    }
                });

                Toast.makeText(this, "New Task Dialog Opened...", Toast.LENGTH_SHORT);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    public void save(View view) {

        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Tasks", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS tasks (list_name VARCHAR,task_name VARCHAR,due_date VARCHAR,checked INT," +
                    "FOREIGN KEY(list_name) REFERENCES Lists(list_name), PRIMARY KEY(task_name))");
            if (listName.isEmpty()) {
                Toast.makeText(this, "List name must be filled!", Toast.LENGTH_SHORT).show();
            } else if (listName.matches(listName)) {
                Toast.makeText(this, "Duplicate text!", Toast.LENGTH_SHORT).show();
            } else {
                String sqlString = "INSERT INTO tasks (list_name, task_name, due_date, completed) VALUES (?,?,?,?)";
                SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
                sqLiteStatement.bindString(1, listName);
                sqLiteStatement.execute();
            }


        } catch (Exception e) {
            System.out.println("Exception occured!");
        }


    }

    public void getData() {

        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Tasks", MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT * FROM Tasks", null);
            int listIx = cursor.getColumnIndex("list_name");
            int taskIx = cursor.getColumnIndex("task_name");
            int dateIx = cursor.getColumnIndex("due_date");
            int checkedIx = cursor.getColumnIndex("checked");
            while (cursor.moveToNext()) {
                //Cursor all database by all index.

            }
            customAdapter.notifyDataSetChanged();
            cursor.close();
        } catch (Exception e) {
            System.out.println("Exception occured!");
        }


    }


}
