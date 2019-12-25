package com.paige.compleroompractice1.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paige.compleroompractice1.R;
import com.paige.compleroompractice1.model.ToDo;

import java.util.ArrayList;
import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.RowCell> {

    private Context context;
    private List<ToDo> toDolist;

    public ToDoAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RowCell onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, null, false);

        return new RowCell(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowCell holder, int position) {

        if(toDolist != null) {
            ToDo toDo = toDolist.get(position);
            holder.tvTodo.setText(toDo.getToDo());
        } else {
            holder.tvTodo.setText(R.string.no_todo);
        }
    }

    public void setToDo(List<ToDo> todos){
        toDolist = todos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(toDolist != null) return toDolist.size(); else return 0;
    }

    class RowCell extends RecyclerView.ViewHolder {

        private TextView tvTodo;

        RowCell(View view) {
            super(view);

            tvTodo = view.findViewById(R.id.textView_itemView);

        }
    }
}

