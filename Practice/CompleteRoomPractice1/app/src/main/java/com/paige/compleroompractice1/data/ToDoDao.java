package com.paige.compleroompractice1.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.paige.compleroompractice1.model.ToDo;

import java.util.List;

@Dao
public interface ToDoDao {

    @Insert
    void insert(ToDo toDo);

    @Query("SELECT * FROM todo_table")
    LiveData<List<ToDo>> getAllTodo();

    @Query("UPDATE todo_table SET todo_col = :toDo WHERE id = :id")
    int updateOneTodo(int id, String toDo);

    @Query("DELETE FROM todo_table")
    void deleteAll();

    @Query("DELETE FROM todo_table WHERE id = :id")
    int deleteOneTodo(int id);

}
