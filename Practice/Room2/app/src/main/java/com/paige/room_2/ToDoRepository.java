package com.paige.room_2;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ToDoRepository {

    private ToDoDao toDoDao;
    private LiveData<List<ToDo>> allToDos;

    public ToDoRepository(Application application){
        ToDoDatabase toDoDatabase = ToDoDatabase.getDatabase(application);
        toDoDao = toDoDatabase.toDoDao();
        allToDos = toDoDao.getAllTodo();
    }

    LiveData<List<ToDo>> getAllToDos() {
        return allToDos;
    }

    void insert(ToDo toDo){
        new InsertAsyncTask(toDoDao).execute(toDo);
    }

    private class InsertAsyncTask extends AsyncTask<ToDo, Void, Void> {

        private ToDoDao toDoDao;

        InsertAsyncTask(ToDoDao toDoDao) {
            this.toDoDao = toDoDao;
        }

        @Override
        protected Void doInBackground(ToDo... toDos) {

            toDoDao.insert(toDos[0]);

            return null;
        }
    }
}
