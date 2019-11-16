package com.example.android_development_assignment_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<TaskItem> al_items;
    private TaskActivity taskActivity;


    CustomAdapter(Context context, ArrayList<TaskItem> al_items, TaskActivity taskActivity) {
        this.context = context;
        this.al_items = al_items;
        this.taskActivity = taskActivity;

    }

    static class ViewHolder {
        TextView tv_name;
        TextView tv_date;
        CheckBox checkbox;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {


            holder = new ViewHolder();
            LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.custom_dialog, parent, false);

            holder.tv_name = (TextView) convertView.findViewById(R.id.task_name);
            holder.tv_date = (TextView) convertView.findViewById(R.id.task_date);
            holder.checkbox = (CheckBox) convertView.findViewById(R.id.completed_checkbox);

            convertView.setTag(holder);


        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_name.setText(al_items.get(position).getName());
        holder.tv_date.setText(al_items.get(position).getDate());
        holder.checkbox.setTag(al_items.get(position).getName());
        //holder.checkbox.setChecked(al_items.get(position).getComplete());

        return convertView;


    }
}
