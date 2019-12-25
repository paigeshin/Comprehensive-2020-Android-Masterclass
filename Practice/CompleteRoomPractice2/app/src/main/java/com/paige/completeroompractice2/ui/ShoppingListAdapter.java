package com.paige.completeroompractice2.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paige.completeroompractice2.R;
import com.paige.completeroompractice2.model.ShoppingList;

import java.text.MessageFormat;
import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingListRowCell>{

    private Context context;
    private List<ShoppingList> shoppingLists;

    public ShoppingListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ShoppingListRowCell onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoppinglist_itemview, parent, false);
        return new ShoppingListRowCell(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListRowCell holder, int position) {
        if(shoppingLists != null){
            holder.tvName.setText(shoppingLists.get(position).getName());
            holder.tvPrice.setText(MessageFormat.format("가격: {0}원", shoppingLists.get(position).getPrice()));
        } else {
            Toast.makeText(context, "No Data Provided", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        if(shoppingLists != null) {
            return shoppingLists.size();
        } else {
            return  0;
        }
    }

    public void setShoppingList(List<ShoppingList> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    class ShoppingListRowCell extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvPrice;

        ShoppingListRowCell(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName_from_shoppingList_itemView);
            tvPrice = itemView.findViewById(R.id.tvPrice_from_shoppingList_itemView);
        }
    }

}
