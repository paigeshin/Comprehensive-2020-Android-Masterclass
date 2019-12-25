package com.paige.completeroompractice2;

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
import com.paige.completeroompractice2.model.ShoppingList;
import com.paige.completeroompractice2.model.ShoppingListViewModel;
import com.paige.completeroompractice2.ui.ShoppingListAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;
    private ShoppingListAdapter shoppingListAdapter;
    private FloatingActionButton fabNext;

    private ShoppingListViewModel shoppingListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
        setRecyclerView();
        setViewModel();
        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNewItem.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    private void bindView(){
        fabNext = findViewById(R.id.floatingActionButton);
    }

    private void setViewModel(){
        shoppingListViewModel = ViewModelProviders.of(this).get(ShoppingListViewModel.class);
        shoppingListViewModel.getAllShoppingList().observe(this, new Observer<List<ShoppingList>>() {
            @Override
            public void onChanged(List<ShoppingList> shoppingLists) {
                shoppingListAdapter.setShoppingList(shoppingLists);

            }
        });
    }

    private void setRecyclerView(){
        RecyclerView rvShoppingList = findViewById(R.id.recyclerView_from_mainActivity);
        shoppingListAdapter = new ShoppingListAdapter(this);
        rvShoppingList.setLayoutManager(new LinearLayoutManager(this));
        rvShoppingList.setAdapter(shoppingListAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            ShoppingList shoppingList = new ShoppingList(data.getStringExtra(AddNewItem.NEW_ITEM_NAME), data.getLongExtra(AddNewItem.NEW_ITEM_PRICE, 0));
            shoppingListViewModel.insertOneShoppingList(shoppingList);
        } else {
            Toast.makeText(this, "NOT SAVED", Toast.LENGTH_SHORT).show();
        }

    }
}
