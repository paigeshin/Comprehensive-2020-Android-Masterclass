package com.paige.room.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paige.room.R;
import com.paige.room.model.NoDo;

import java.util.List;
import java.util.zip.Inflater;

public class NoDoListAdapter extends RecyclerView.Adapter<NoDoListAdapter.NoDoViewHolder> {

    private final LayoutInflater noDoInflator;
    private List<NoDo> noDoList;

    public NoDoListAdapter(Context context) {
        noDoInflator = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NoDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = noDoInflator.inflate(R.layout.recyclerview_item, parent, false);

        return new NoDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoDoViewHolder holder, int position) {

        if(noDoList != null){
            NoDo current = noDoList.get(position);
            holder.noDoTextView.setText(current.getNoDo());
        } else {
            holder.noDoTextView.setText(R.string.no_nodo);
        }

    }

    public void setNoDo(List<NoDo> noDos){
        noDoList = noDos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(noDoList != null){
            return noDoList.size();
        } else {
            return 0;
        }
    }

    class NoDoViewHolder extends RecyclerView.ViewHolder{

        private TextView noDoTextView;

        NoDoViewHolder(@NonNull View itemView) {
            super(itemView);
            noDoTextView = itemView.findViewById(R.id.textView);
        }
    }
}
