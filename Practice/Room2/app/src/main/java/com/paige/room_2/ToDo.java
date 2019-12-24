package com.paige.room_2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_table")
public class ToDo {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "col_todo")
    private String toDo;

    public ToDo(@NonNull String toDo) {
        this.toDo = toDo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getToDo() {
        return toDo;
    }

    public void setToDo(@NonNull String toDo) {
        this.toDo = toDo;
    }
}
