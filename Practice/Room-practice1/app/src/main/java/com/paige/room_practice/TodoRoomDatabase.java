package com.paige.room_practice;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ToDo.class}, version = 1)
public abstract class TodoRoomDatabase extends RoomDatabase {

    //Don't want to create a lot of instances, singleton
    //Volatile means "don't store in the cache"
    private static volatile TodoRoomDatabase INSTANCE;
    public abstract ToDoDao toDoDao();

    public static TodoRoomDatabase getDatabase(Context context){
        if(INSTANCE == null){
            //synchronized keyword, for UI Thread, force it to work as it is supposed to be
            synchronized (TodoRoomDatabase.class){
                if(INSTANCE == null){
                    //create our db
                    INSTANCE = Room.databaseBuilder(context, TodoRoomDatabase.class, "todo_database").build();
                }
            }
        }
        return INSTANCE;
    }

}
