package com.paige.room_practice;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ToDoRepository {

    private ToDoDao toDoDao;
    private LiveData<List<ToDo>> allToDos;

    public ToDoRepository(Application application) {
        TodoRoomDatabase db = TodoRoomDatabase.getDatabase(application);
        toDoDao = db.toDoDao();
        allToDos = toDoDao.getAllToDos();
    }

    LiveData<List<ToDo>> getAllToDos(){
        return allToDos;
    }

    //AsyncTask
    void insert(ToDo toDo){
        new insertAsyncTask(toDoDao).execute(toDo);
    }

    private class insertAsyncTask extends AsyncTask<ToDo, Void, Void> {

        private ToDoDao toDoDao;

        insertAsyncTask(ToDoDao toDoDao) {
            this.toDoDao = toDoDao;
        }

        @Override
        protected Void doInBackground(ToDo... toDos) {
            toDoDao.insert(toDos[0]);
            return null;
        }
    }
}
