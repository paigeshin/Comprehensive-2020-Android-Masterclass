package com.paige.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.paige.myapplication.data.DatabaseHandler;
import com.paige.myapplication.model.Item;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class AppActivity extends AppCompatActivity {

    public static final String TAG = "AppAct";

    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private Button saveButton;
    private EditText babyItem, itemQuantity, itemColor, itemSize;

    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHandler(AppActivity.this);
        byPassActivity();

        //check if item was saved
        List<Item> items = db.getAllItems();
        for(Item item : items){
            Log.d(TAG, "onCreate: " + item.getItemName());
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                createPopupDialog();
            }


        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void byPassActivity(){
        if(db.getItemCount() > 0){
            startActivity(new Intent(AppActivity.this, ListActivity.class));
            finish();
        }
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

        db.addItem(item);

        Snackbar.make(view, "Item Saved", Snackbar.LENGTH_SHORT).show();

        //Todo: move to next screen - details screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //code to be run
                dialog.dismiss();
                //Todo: move to next screen - details screen
                startActivity(new Intent(AppActivity.this, ListActivity.class));
            }
        }, 1200); // 1sec
    }

    private void createPopupDialog() {

        builder = new AlertDialog.Builder(AppActivity.this);
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

}
