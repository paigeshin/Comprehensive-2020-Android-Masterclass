package com.paige.compleroompractice1.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.paige.compleroompractice1.model.ToDo;

@Database(entities = {ToDo.class}, version = 1)
public abstract class ToDoDatabase extends RoomDatabase {

    private static volatile ToDoDatabase INSTANCE;
    public abstract ToDoDao toDoDao();

    public static ToDoDatabase getDatabase(Context context){
        if(INSTANCE == null){
            synchronized (ToDoDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context, ToDoDatabase.class, "todo_database")
                            .addCallback(roomDatabaseCallBack)
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    //callback
    private static RoomDatabase.Callback roomDatabaseCallBack =
            new RoomDatabase.Callback(){
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDBAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDBAsync extends AsyncTask<Void, Void, Void>{
        private final ToDoDao toDoDao;

        PopulateDBAsync(ToDoDatabase db) {
            this.toDoDao = db.toDoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            toDoDao.deleteAll(); //removes all items from our table
            //for testing
            ToDo toDo = new ToDo("Buy a new Ferrari");
            toDoDao.insert(toDo);

            toDo = new ToDo("Buy a Big House");
            toDoDao.insert(toDo);
            return null;
        }
    }

}
