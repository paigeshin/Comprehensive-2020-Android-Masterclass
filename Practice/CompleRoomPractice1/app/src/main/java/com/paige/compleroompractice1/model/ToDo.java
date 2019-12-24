package com.paige.compleroompractice1.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "todo_table")
public class ToDo {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "todo_col")
    private String toDo;

    public ToDo(@NotNull String toDo) {
        this.toDo = toDo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public String getToDo() {
        return toDo;
    }

    public void setToDo(@NotNull String toDo) {
        this.toDo = toDo;
    }
}
