package com.paige.room.util;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.paige.room.data.NoDoDao;
import com.paige.room.model.NoDo;
import com.paige.room.model.NoDoRoomDatabase;

import java.util.List;

public class NoDoRepository {

    private NoDoDao noDoDao;
    private LiveData<List<NoDo>> allNoDos;

    public NoDoRepository(Application application) {
        //Get data from a remote API and then put it on a diff. list
        NoDoRoomDatabase db = NoDoRoomDatabase.getDatabase(application);
        noDoDao = db.noDoDao();
        allNoDos = noDoDao.getAllNoDos();
    }

    LiveData<List<NoDo>> getAllNoDos(){
        return allNoDos;
    }

    //Async Task
    void insert(NoDo noDo){
        new insertAsyncTask(noDoDao).execute(noDo);
    }

    private class insertAsyncTask extends AsyncTask<NoDo, Void, Void> {

        private NoDoDao asyncTaskDao;

        insertAsyncTask(NoDoDao dao) {
            asyncTaskDao = dao;

        }

        @Override
        protected Void doInBackground(NoDo... noDos) {
            //[obj1, obj2...]
            asyncTaskDao.insert(noDos[0]);
            return null;
        }
    }
}
