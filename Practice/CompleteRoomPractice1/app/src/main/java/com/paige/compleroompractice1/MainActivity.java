package com.paige.compleroompractice1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.paige.compleroompractice1.model.ToDo;
import com.paige.compleroompractice1.model.ToDoViewModel;
import com.paige.compleroompractice1.ui.ToDoAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_TODO_REQUEST_CODE = 1;
    private RecyclerView recyclerView;
    private ToDoAdapter toDoAdapter;
    private ToDoViewModel toDoViewModel;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toDoViewModel = ViewModelProviders.of(MainActivity.this).get(ToDoViewModel.class);

        recyclerView = findViewById(R.id.recyclerView);
        toDoAdapter = new ToDoAdapter(MainActivity.this);
        recyclerView.setAdapter(toDoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewToDoActivity.class);
                startActivityForResult(intent, NEW_TODO_REQUEST_CODE); //Request Code
            }
        });

        toDoViewModel.getAllToDos().observe(MainActivity.this, new Observer<List<ToDo>>() {
            @Override
            public void onChanged(List<ToDo> toDos) {
                toDoAdapter.setToDo(toDos);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_TODO_REQUEST_CODE && resultCode == RESULT_OK){
            ToDo toDo = new ToDo(data.getStringExtra(NewToDoActivity.EXTRA_REPLY));
            toDoViewModel.insert(toDo);
        } else {
            Toast.makeText(MainActivity.this, R.string.not_saved, Toast.LENGTH_SHORT).show();
        }
    }
}
