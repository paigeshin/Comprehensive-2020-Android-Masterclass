package com.paige.room_to_do_list;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.paige.room_to_do_list.mvvm.todo.ToDo;
import com.paige.room_to_do_list.mvvm.todo.ToDoRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivityViewModel extends AndroidViewModel {

    //ViewModel에서는 Dao를 소환하지 않는다

    //Repository 소환
    private ToDoRepository toDoRepository;
    //모든 데이터.
    private LiveData<List<ToDo>> toDoList;

    private Application application;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        toDoRepository = new ToDoRepository(application);
        toDoList = toDoRepository.getAllToDos();
    }

    public LiveData<List<ToDo>> getAllToDo(){
        return toDoRepository.getAllToDos();
    }

    public void insertToDo(ToDo toDo){
        toDoRepository.insertToDo(toDo);
    }

    public void updateToDo(ToDo toDo) {
            toDoRepository.updateToDo(toDo);
    }

    public void deleteToDo(int id){
            toDoRepository.deleteToDo(id);
    }

    public LiveData<ToDo> getOneToDo(int id){
        return toDoRepository.getToDo(id);
    }

}
