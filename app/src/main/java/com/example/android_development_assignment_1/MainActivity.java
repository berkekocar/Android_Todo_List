package com.example.android_development_assignment_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.LocusId;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText et_todoListName;
    private Button bt_create_list;
    private TextView tv_display;
    private ListView lv_mainlist;
    private ArrayList<String> al_strings;
    private ArrayAdapter<String> aa_strings;
    MenuItem item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_display = (TextView) findViewById(R.id.tv_display);
        lv_mainlist = (ListView) findViewById(R.id.lv_mainlist);

        al_strings = new ArrayList<String>();
        al_strings.add("First Row!");


        aa_strings = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al_strings);
        lv_mainlist.setAdapter(aa_strings);

        lv_mainlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, TaskActivity.class);
                intent.putExtra("List Name", lv_mainlist.toString());
                startActivity(intent);
            }
        });
        lv_mainlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Edit and Delete
                return false;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.list_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mn_list_name) {
            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {


                    return false;
                }
            });
            Toast.makeText(MainActivity.this, "Directed to Add Text Dialog...", Toast.LENGTH_LONG).show();
            simpleInputDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    private void simpleInputDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Add a list name");
        final EditText et = new EditText(this);
        et.setInputType(InputType.TYPE_CLASS_TEXT);
        et.setHint("To-Do List Name: ");
        builder.setView(et);

        builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                al_strings.add(et.getText().toString());
                aa_strings.notifyDataSetChanged();
                tv_display.setText("There are " + al_strings.size() + " item in the list.");

                Toast.makeText(MainActivity.this, "User Input is: " + et.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();


    }


}