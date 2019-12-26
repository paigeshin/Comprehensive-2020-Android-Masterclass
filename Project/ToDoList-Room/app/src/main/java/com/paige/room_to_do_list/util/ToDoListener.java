package com.paige.room_to_do_list.util;

import com.paige.room_to_do_list.mvvm.todo.ToDo;

public interface ToDoListener {

    void todoClickListener(int id);
    void todoUpdateListener(ToDo toDo);
    void todoDeleteListener(int id);

}
