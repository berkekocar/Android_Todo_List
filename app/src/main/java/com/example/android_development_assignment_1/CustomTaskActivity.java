package com.example.android_development_assignment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class CustomTaskActivity extends AppCompatActivity {

    EditText et_task_name;
    TextView tv_task_date;
    CalendarView cv_task_date;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_task);

        et_task_name = findViewById(R.id.et_task_name);
        tv_task_date = findViewById(R.id.tv_task_date);
        cv_task_date = findViewById(R.id.cv_task_date);
        btn = findViewById(R.id.btn);





    }
}
