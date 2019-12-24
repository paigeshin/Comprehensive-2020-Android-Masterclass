package com.paige.room.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.paige.room.data.NoDoDao;

@Database(entities = {NoDo.class}, version = 1)
public abstract class NoDoRoomDatabase extends RoomDatabase {

    //Don't want to create a lot of instances, singleton
    //volatile means "don't store in the cache"
    private static volatile NoDoRoomDatabase INSTANCE;
    public abstract NoDoDao noDoDao();

    public static NoDoRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            //synchronized keyword, for UI Thread, force it to work as it is supposed to do
            synchronized (NoDoRoomDatabase.class){
                if(INSTANCE == null){
                    //create our db
                    INSTANCE = Room.databaseBuilder(context, NoDoRoomDatabase.class, "nodo_database").build();
                }
            }
        }
        return INSTANCE;
    }
            
}
