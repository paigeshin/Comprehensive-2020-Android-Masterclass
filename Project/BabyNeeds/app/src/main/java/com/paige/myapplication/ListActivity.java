package com.paige.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.paige.myapplication.data.DatabaseHandler;
import com.paige.myapplication.model.Item;
import com.paige.myapplication.util.Constants;
import com.paige.myapplication.util.RecyclerViewAdapter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    public static final String TAG = "ListAct";

    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private Button saveButton;
    private EditText babyItem, itemQuantity, itemColor, itemSize;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Item> itemList;
    private DatabaseHandler db;

    private FloatingActionButton fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        fb = findViewById(R.id.floatingActionButtonListActivity);

        db = new DatabaseHandler(ListActivity.this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));

        itemList = new ArrayList<>();

        //Get items from db
        itemList = db.getAllItems();

        for(Item item : itemList){
            Log.d(TAG, "onCreate: " + item.getItemName());
        }

        recyclerViewAdapter = new RecyclerViewAdapter(ListActivity.this, itemList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPopupDialog();
            }
        });

    }

    private void createPopupDialog() {

        builder = new AlertDialog.Builder(ListActivity.this);
        View view = getLayoutInflater().inflate(R.layout.popup, null);
        babyItem = view.findViewById(R.id.babyItem);
        itemQuantity = view.findViewById(R.id.itemQuantity);
        itemColor = view.findViewById(R.id.itemColor);
        itemSize = view.findViewById(R.id.itemSize);

        saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!babyItem.getText().toString().isEmpty()
                        && !itemColor.getText().toString().isEmpty()
                        && !itemQuantity.getText().toString().isEmpty()
                        && !itemSize.getText().toString().isEmpty()){
                    saveItem(view);
                } else {
                    Snackbar.make(view, "Empty Fields not Allowed", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        builder.setView(view);
        dialog = builder.create();
        dialog.show();

    }

    private void saveItem(View view){
        //Todo: save each baby item to database
        Item item = new Item();
        String newItem = babyItem.getText().toString().trim();
        String newColor = itemColor.getText().toString().trim();
        int quantity = Integer.parseInt(itemQuantity.getText().toString().trim());
        int size = Integer.parseInt(itemSize.getText().toString().trim());

        item.setItemName(newItem);
        item.setItemColor(newColor);
        item.setItemQuantity(quantity);
        item.setItemSize(size);

        //convert Timestamp to something readable
        DateFormat dateFormat = DateFormat.getDateInstance();
        String formattedDate = dateFormat.format(System.currentTimeMillis()); // Feb 23, 2020
        item.setDateItemAdded(formattedDate);

        db.addItem(item);
        itemList.add(item);
        recyclerViewAdapter.notifyDataSetChanged();

        Snackbar.make(view, "Item Saved", Snackbar.LENGTH_SHORT).show();

    }

    @Override
    protected void onPause() {
        super.onPause();
//        db.deleteAll();
    }
}
