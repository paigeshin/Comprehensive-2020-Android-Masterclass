package com.paige.room_2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ToDo.class}, version = 1)
public abstract class ToDoDatabase extends RoomDatabase {

    private static volatile ToDoDatabase INSTANCE;
    public abstract ToDoDao toDoDao();

    public static ToDoDatabase getDatabase(Context context){
        if(INSTANCE == null){
            synchronized (ToDoDatabase.class){
                INSTANCE = Room.databaseBuilder(context, ToDoDatabase.class, "todo_database").build();
            }
        }
        return INSTANCE;
    }

}
