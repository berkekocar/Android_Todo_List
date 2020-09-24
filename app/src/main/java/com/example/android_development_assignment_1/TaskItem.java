package com.example.android_development_assignment_1;

public class TaskItem {

    private String list, name, date;
    private int checked;

    public TaskItem(String list, String name, String date, int checked) {
        this.list = list;
        this.name = name;
        this.date = date;
        this.checked = checked;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getComplete() {
        return checked;
    }

    public void setComplete(int complete) {
        this.checked = complete;
    }


}
