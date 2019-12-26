package com.paige.room_to_do_list.mvvm.todo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ToDoDao {

    //CRUD

    @Insert
    void insertToDo(ToDo toDo);

    @Query("SELECT * FROM to_do_list")
    LiveData<List<ToDo>> getAllToDos();

    @Query("SELECT * FROM to_do_list WHERE id=:id")
    LiveData<ToDo> getToDo(int id);

    @Query("UPDATE to_do_list SET todo=:todo, date=:date WHERE id=:id")
    void updateToDo(int id, String todo, long date);

    @Query("DELETE FROM to_do_list WHERE id=:id")
    void deleteToDo(int id);

    @Query("DELETE FROM to_do_list")
    void deleteAll();

}
