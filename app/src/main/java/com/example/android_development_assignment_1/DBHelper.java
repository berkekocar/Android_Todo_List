package com.example.android_development_assignment_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table_1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(drop_table_1);
        db.execSQL(create_table_1);

    }

    private static final String create_table_1 = "create table todo_list(" +
            "todo_list_name TEXT PRIMARY KEY)";

    private static final String drop_table_1 = "drop table test";


}

