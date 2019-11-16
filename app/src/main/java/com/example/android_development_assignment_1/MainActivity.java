package com.example.android_development_assignment_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.LocusId;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
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
    private ListView lv_mainlist;
    private ArrayList<String> al_strings;
    private ArrayAdapter<String> aa_strings;
    ArrayAdapter arrayAdapter;
    String listName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

               //Edit or Delete


                return false;
            }
        });
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Lists", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS lists (list_name VARCHAR PRIMARY KEY)");
        } catch (Exception e) {
            System.out.println("Exception occured!");
        }

        getData();


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
                listName = et.getText().toString();
                save(et);


                Toast.makeText(MainActivity.this, "User Input is: " + et.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    }

    public void save(View view) {

        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Lists", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS lists (list_name VARCHAR PRIMARY KEY)");
            if (listName.isEmpty()) {
                Toast.makeText(MainActivity.this, "List name must be filled!", Toast.LENGTH_SHORT).show();
            } else if (listName.matches(listName)) {
                Toast.makeText(MainActivity.this, "Duplicate text!", Toast.LENGTH_SHORT).show();
            } else {
                String sqlString = "INSERT INTO lists (list_name) VALUES (?)";
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
            SQLiteDatabase database = this.openOrCreateDatabase("Lists", MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT * FROM Lists", null);
            int nameIx = cursor.getColumnIndex("list_name");
            while (cursor.moveToNext()) {
                al_strings.add(cursor.getString(nameIx));
            }
            aa_strings.notifyDataSetChanged();
            cursor.close();
        } catch (Exception e) {
            System.out.println("Exception occured!");
        }


    }


}