package com.example.android_development_assignment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private DBHelper db;
    private SQLiteDatabase sdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db = new DBHelper(this, "todo_list", null, 1);
        sdb = db.getWritableDatabase();
    }
}
