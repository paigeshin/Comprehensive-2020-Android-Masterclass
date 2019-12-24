package com.paige.room_2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ToDoDao {

    @Insert
    void insert(ToDo toDo);

    @Query("UPDATE todo_table SET col_todo = :toDo WHERE id = :id")
    int updateToDo(int id, String toDo);

    @Query("DELETE FROM todo_table")
    int deleteAll();

    @Query("DELETE FROM todo_table WHERE id = :id")
    int deleteToDo(int id);

    @Query("SELECT * FROM todo_table")
    LiveData<List<ToDo>> getAllTodo();



}
