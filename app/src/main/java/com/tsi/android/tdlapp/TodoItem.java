package com.tsi.android.tdlapp;


import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

import java.util.Date;

public class TodoItem implements Parcelable, BaseColumns {

    private int id;
    private String title;
    private String description;
    private Date checkedDate;
    private Date date;
    private boolean checked;

    public TodoItem() {
    }

    public TodoItem(int id, String title, Date date, boolean checkbox, Date checkedDate, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.checkedDate = checkedDate;
        this.date = date;
        this.checked = checkbox;
    }

    public TodoItem(String title, Date date, boolean checkbox) {
        this.title = title;
        this.date = date;
        this.checked = checkbox;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public Date getCheckedDate() {
        return checkedDate;
    }

    public void setCheckedDate(Date checkedDate) {
        this.checkedDate = checkedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeSerializable(checkedDate);
        dest.writeSerializable(date);
        dest.writeByte((byte) (checked ? 1 : 0));
    }

    protected TodoItem(Parcel in) {
        title = in.readString();
        description = in.readString();
        checkedDate = (Date) in.readSerializable();
        date = (Date) in.readSerializable();
        checked = in.readByte() != 0;
    }

    public static final Creator<TodoItem> CREATOR = new Creator<TodoItem>() {
        @Override
        public TodoItem createFromParcel(Parcel in) {
            return new TodoItem(in);
        }

        @Override
        public TodoItem[] newArray(int size) {
            return new TodoItem[size];
        }
    };

}
