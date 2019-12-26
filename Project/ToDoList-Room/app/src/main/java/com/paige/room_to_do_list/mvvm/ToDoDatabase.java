package com.paige.room_to_do_list.mvvm;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.paige.room_to_do_list.mvvm.todo.ToDo;
import com.paige.room_to_do_list.mvvm.todo.ToDoDao;

@Database(entities = {ToDo.class}, version = 1)
public abstract class ToDoDatabase extends RoomDatabase {

    private static volatile ToDoDatabase instance;
    public abstract ToDoDao toDoDao();

    public static ToDoDatabase getDatabase(Context context){
        if(instance == null){
            synchronized (ToDoDatabase.class){
                if(instance == null){
                    instance = Room
                            .databaseBuilder(context, ToDoDatabase.class, "todo_database")
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            new PopulateDBAsyncTask(instance).execute();

        }

    };

    //아래 코드 문제가 생길 가능성이 있음. 같은 클래스 내부에 있으면, instance화를 안해도 되나?
    //=> 안해도 된다. 이 코드를 호출하려면 어차피 생성자를 만들어야 한다.
    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void>{

        private ToDoDao toDoDao;

        private PopulateDBAsyncTask(ToDoDatabase toDoDatabase) {
            toDoDao = toDoDatabase.toDoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

//            toDoDao.deleteAll();
//            toDoDao.insertToDo(new ToDo("Work out", System.currentTimeMillis()));
//            toDoDao.insertToDo(new ToDo("Meditate", System.currentTimeMillis()));


            return null;
        }
    }


}
