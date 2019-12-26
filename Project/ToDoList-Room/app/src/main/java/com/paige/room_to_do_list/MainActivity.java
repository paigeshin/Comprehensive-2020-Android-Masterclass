package com.paige.room_to_do_list;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.paige.room_to_do_list.mvvm.todo.ToDo;
import com.paige.room_to_do_list.ui.ToDoAdapter;
import com.paige.room_to_do_list.util.Common;
import com.paige.room_to_do_list.util.ToDoListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ToDoListener {

    private MainActivityViewModel mainActivityViewModel;
    private RecyclerView recyclerView;
    private ToDoAdapter toDoAdapter;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        toDoAdapter = new ToDoAdapter(this, this);
        recyclerView.setAdapter(toDoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getAllToDo().observe(this, new Observer<List<ToDo>>() {
            @Override
            public void onChanged(List<ToDo> toDos) {
                toDoAdapter.setToDoList(toDos);
                toDoAdapter.notifyDataSetChanged();
            }
        });

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInsertDialog();
            }
        });
    }

    private void openInsertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog, null);
        final EditText etToDo = view.findViewById(R.id.etDialogUpdateToDo);
        final TextView tvDate = view.findViewById(R.id.tvDialogUpdateDate);
        Button btnConfirm = view.findViewById(R.id.dialogUpdateButton);

        etToDo.setText("");
        tvDate.setText(Common.getFormattedDate(System.currentTimeMillis()));
        btnConfirm.setText(R.string.insert);

        builder.setView(view);
        final AlertDialog dialog = builder.create();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!TextUtils.isEmpty(etToDo.getText().toString())) {
                    ToDo toDo = new ToDo();
                    toDo.setTodo(etToDo.getText().toString());
                    toDo.setDate(System.currentTimeMillis());
                    mainActivityViewModel.insertToDo(toDo);
                    toDoAdapter.notifyDataSetChanged();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                        }
                    }, 300);
                } else {
                    Snackbar.make(v, "Please fill out the new task", Snackbar.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

            }
        });
        dialog.show();
    }

    @Override
    public void todoClickListener(int id) {
        mainActivityViewModel.getOneToDo(id).observe(this, new Observer<ToDo>() {
            @Override
            public void onChanged(ToDo toDo) {
                    openToDoDialog(toDo);
            }
        });

    }

    private void openToDoDialog(ToDo toDo){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View view = getLayoutInflater().inflate(R.layout.dialog_show, null);
        TextView tvToDo = view.findViewById(R.id.dialogToDo);
        TextView tvDate = view.findViewById(R.id.dialogDate);
        Button btnConfirm = view.findViewById(R.id.dialogButton);

        tvToDo.setText(toDo.getTodo());
        tvDate.setText(Common.getFormattedDate(toDo.getDate()));

        builder.setView(view);
        final AlertDialog dialog = builder.create();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    @Override
    public void todoUpdateListener(ToDo toDo) {
        openUpdateDialog(toDo);
    }

    private void openUpdateDialog(final ToDo toDo){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View view = getLayoutInflater().inflate(R.layout.dialog, null);
        final EditText etToDo = view.findViewById(R.id.etDialogUpdateToDo);
        final TextView tvDate = view.findViewById(R.id.tvDialogUpdateDate);
        Button btnConfirm = view.findViewById(R.id.dialogUpdateButton);

        etToDo.setText(toDo.getTodo());
        tvDate.setText(Common.getFormattedDate(toDo.getDate()));

        builder.setView(view);
        final AlertDialog dialog = builder.create();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(etToDo.getText().toString())) {
                    ToDo updatedToDo = new ToDo();
                    updatedToDo.setId(toDo.getId());
                    updatedToDo.setTodo(etToDo.getText().toString());
                    updatedToDo.setDate(System.currentTimeMillis());
                    mainActivityViewModel.updateToDo(updatedToDo);
                    toDoAdapter.notifyDataSetChanged();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                        }
                    }, 300);
                } else {
                    Snackbar.make(v, "Please fill out the new task", Snackbar.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

            }
        });
        dialog.show();
    }

    @Override
    public void todoDeleteListener(int id) {
        openDeleteDialog(id);
    }

    private void openDeleteDialog(final int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("DELETE ITEM")
                .setMessage("Are you sure to delete this item?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mainActivityViewModel.deleteToDo(id);
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create()
                .show();
    }

}
