package com.paige.compleroompractice1.util;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.paige.compleroompractice1.data.ToDoDao;
import com.paige.compleroompractice1.data.ToDoDatabase;
import com.paige.compleroompractice1.model.ToDo;

import java.util.List;

public class ToDoRepository {
    //DAO => 기본적으로 Database든, Repository든 ViewModel이든 모두 DAO를 소환
    private ToDoDao toDoDao;
    //모든 데이터
    private LiveData<List<ToDo>> allToDo;

    public ToDoRepository(Application application) {
        ToDoDatabase db = ToDoDatabase.getDatabase(application);
        toDoDao = db.toDoDao();
        allToDo = toDoDao.getAllTodo();
    }

    public LiveData<List<ToDo>> getAllTodo(){
        return allToDo;
    }
    //실질적으로 여기다가 CRUD model을 정의
    public void insert(ToDo toDo){
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
