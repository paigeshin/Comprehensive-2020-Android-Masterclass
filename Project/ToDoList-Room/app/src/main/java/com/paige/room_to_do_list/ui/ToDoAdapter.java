package com.paige.room_to_do_list.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.paige.room_to_do_list.R;
import com.paige.room_to_do_list.mvvm.todo.ToDo;
import com.paige.room_to_do_list.util.Common;
import com.paige.room_to_do_list.util.ToDoListener;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoRow>{

    public static final String TAG = "ToDoAdapter";
    private List<ToDo> toDoList;
    private Context context;
    private ToDoListener toDoListener;

    public ToDoAdapter(Context context, ToDoListener toDoListener) {
        this.context = context;
        this.toDoListener = toDoListener;
    }

    @NonNull
    @Override
    public ToDoRow onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, parent, false);

        return new ToDoRow(itemView, toDoListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoRow holder, int position) {
        if(toDoList != null){
            ToDo toDo = toDoList.get(position);
            holder.tvTodo.setText(toDo.getTodo());
            holder.tvDate.setText(Common.getFormattedDate(toDo.getDate()));
        } else {
            holder.tvTodo.setText(R.string.No_Value);
        }
    }

    public void setToDoList(List<ToDo> toDoList){
        this.toDoList = toDoList;
    }



    @Override
    public int getItemCount() {
        if(toDoList != null){
            return toDoList.size();
        } else {
            return 0;
        }
    }



    class ToDoRow extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvTodo, tvDate;
        private ImageView ivModify, ivDelete;
        private ToDoListener toDoListener;


        ToDoRow(@NonNull View itemView, ToDoListener toDoListener) {
            super(itemView);
            tvTodo = itemView.findViewById(R.id.tvTodo);
            tvDate = itemView.findViewById(R.id.tvDate);
            ivModify = itemView.findViewById(R.id.ivModify_dialog);
            ivDelete = itemView.findViewById(R.id.ivDelete_dialog);
            this.toDoListener = toDoListener;

            ivModify.setOnClickListener(this);
            ivDelete.setOnClickListener(this);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ToDo toDo = toDoList.get(position);
            Log.d(TAG, "id : " + toDo.getId());
            Log.d(TAG, "toDo : " + toDo.getTodo());
            Log.d(TAG, "date : " + toDo.getDate());
            switch (v.getId()) {
                case R.id.ivModify_dialog:
                    toDoListener.todoUpdateListener(toDo);
                    break;
                case R.id.ivDelete_dialog:
                    toDoListener.todoDeleteListener(toDo.getId());
                    break;
                default:
                    toDoListener.todoClickListener(toDo.getId());
            }
        }


    }

}
