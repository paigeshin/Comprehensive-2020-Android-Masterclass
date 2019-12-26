package com.paige.room_to_do_list.mvvm.todo;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.paige.room_to_do_list.mvvm.ToDoDatabase;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ToDoRepository {

    private static final String TAG = "ToDoRepo";

    //DAO와 가져올 데이터를 선언
    private ToDoDao toDoDao;
    private LiveData<List<ToDo>> toDoList;

    public ToDoRepository(Application application) {
        ToDoDatabase db = ToDoDatabase.getDatabase(application);
        toDoDao = db.toDoDao();
        toDoList = toDoDao.getAllToDos();
    }

    public LiveData<List<ToDo>> getAllToDos() {
        return toDoList;
    }

    public LiveData<ToDo> getToDo(int position) {
        return toDoDao.getToDo(position);
    }

    public void insertToDo(ToDo toDo){
        new InsertAsyncTask(toDoDao).execute(toDo);
    }

    public void updateToDo(ToDo toDo) {
        new UpdateAsyncTask(toDoDao).execute(toDo);
    }

    public void deleteToDo(int id) {
        new DeleteAsyncTask(toDoDao).execute(id);
    }


    private static class InsertAsyncTask extends AsyncTask<ToDo, Void, Void>{

        private ToDoDao toDoDao;

        InsertAsyncTask(ToDoDao toDoDao) {
            this.toDoDao = toDoDao;
        }

        @Override
        protected Void doInBackground(ToDo... toDos) {
            toDoDao.insertToDo(toDos[0]);
            Log.d(TAG, "toDoDao.insertToDo(toDos[0])");
            return null;
        }

    }

    private static class UpdateAsyncTask extends AsyncTask<ToDo, Void, Void> {

        private ToDoDao toDoDao;

        private UpdateAsyncTask(ToDoDao toDoDao) {
            this.toDoDao = toDoDao;
        }

        @Override
        protected Void doInBackground(ToDo... toDos) {
            ToDo toDo = toDos[0];
            Log.d(TAG, "id : " + toDo.getId());
            Log.d(TAG, "todo : " + toDo.getTodo());
            Log.d(TAG, "date : " + toDo.getDate());
            toDoDao.updateToDo(toDo.getId(), toDo.getTodo(), toDo.getDate());
            Log.d(TAG, "toDoDao.updateToDo(toDos[0])");
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Integer, Void, Void> {

        private ToDoDao toDoDao;

        private DeleteAsyncTask(ToDoDao toDoDao) {
            this.toDoDao = toDoDao;
        }

        @Override
        protected Void doInBackground(Integer... ids) {
            Log.d(TAG, "toDoDao.deleteToDo(toDos[0])");
            toDoDao.deleteToDo(ids[0]);
            return null;
        }

    }

}
