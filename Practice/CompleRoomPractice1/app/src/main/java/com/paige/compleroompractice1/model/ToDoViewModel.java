package com.paige.compleroompractice1.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.paige.compleroompractice1.util.ToDoRepository;

import java.util.List;

public class ToDoViewModel extends AndroidViewModel {

    //여기서는 Dao를 호출하지 않는다.
    //대신 Repository를 호출
    private ToDoRepository toDoRepository;
    private LiveData<List<ToDo>> allToDos;

    public ToDoViewModel(@NonNull Application application) {
        super(application);
        toDoRepository = new ToDoRepository(application);
        allToDos = toDoRepository.getAllTodo();
    }

    //repository에서 정의한 method를 다시 한 번 정의
    public LiveData<List<ToDo>> getAllToDos() {
        return allToDos;
    }

    public void insert(ToDo toDo){
        toDoRepository.insert(toDo);
    }

}
