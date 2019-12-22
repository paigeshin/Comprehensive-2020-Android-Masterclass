package com.paige.myapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.paige.myapplication.model.Item;
import com.paige.myapplication.util.Constants;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String TAG = "DBHandler";
    private Context context;

    public DatabaseHandler(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BABY_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "("
                + Constants.KEY_ID + " INTEGER PRIMARY KEY,"
                + Constants.KEY_BABY_ITEM + " INTEGER,"
                + Constants.KEY_COLOR + " TEXT,"
                + Constants.KEY_QTY_NUMBER + " TEXT,"
                + Constants.KEY_ITEM_SIZE + " INTEGER,"
                + Constants.KEY_DATE_NAME + " LONG);";

        db.execSQL(CREATE_BABY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }

    // CRUD operations
    public void addItem(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.KEY_BABY_ITEM, item.getItemName());
        contentValues.put(Constants.KEY_COLOR, item.getItemColor());
        contentValues.put(Constants.KEY_QTY_NUMBER, item.getItemQuantity());
        contentValues.put(Constants.KEY_ITEM_SIZE, item.getItemSize());
        contentValues.put(Constants.KEY_DATE_NAME, System.currentTimeMillis());

        //Insert the row
        db.insert(Constants.TABLE_NAME, null, contentValues);

        db.close();
        Log.d(TAG, "added Item Color: " + contentValues.get(Constants.KEY_COLOR) );
    }

    //Get an Item
    public Item getItem(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                Constants.TABLE_NAME,
                new String[]{Constants.KEY_ID, Constants.KEY_BABY_ITEM, Constants.KEY_COLOR, Constants.KEY_QTY_NUMBER, Constants.KEY_ITEM_SIZE, Constants.KEY_DATE_NAME},
                Constants.KEY_ID + " = ?",
                new String[]{String.valueOf(id)},
                null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();

        Item item = new Item();
        if(cursor != null) {
            item.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
            item.setItemName(cursor.getString(cursor.getColumnIndex(Constants.KEY_BABY_ITEM)));
            item.setItemColor(cursor.getString(cursor.getColumnIndex(Constants.KEY_COLOR)));
            item.setItemQuantity(cursor.getInt(cursor.getColumnIndex(Constants.KEY_QTY_NUMBER)));
            item.setItemSize(cursor.getInt(cursor.getColumnIndex(Constants.KEY_ITEM_SIZE)));

            //convert Timestamp to something readable
            DateFormat dateFormat = DateFormat.getDateInstance();
            String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_NAME))).getTime()); // Feb 23, 2020
            item.setDateItemAdded(formattedDate);
        }
        return item;
    }

    //Get all Items
    public List<Item> getAllItems(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Item> itemList = new ArrayList<>();
        Cursor cursor = db.query
                (Constants.TABLE_NAME,
                new String[]{Constants.KEY_ID, Constants.KEY_BABY_ITEM, Constants.KEY_COLOR, Constants.KEY_QTY_NUMBER, Constants.KEY_ITEM_SIZE, Constants.KEY_DATE_NAME},
                null, null, null, null, Constants.KEY_DATE_NAME + " DESC", null);

        if(cursor.moveToFirst()){
            do {
                Item item = new Item();
                item.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
                item.setItemName(cursor.getString(cursor.getColumnIndex(Constants.KEY_BABY_ITEM)));
                item.setItemColor(cursor.getString(cursor.getColumnIndex(Constants.KEY_COLOR)));
                item.setItemQuantity(cursor.getInt(cursor.getColumnIndex(Constants.KEY_QTY_NUMBER)));
                item.setItemSize(cursor.getInt(cursor.getColumnIndex(Constants.KEY_ITEM_SIZE)));

                //convert Timestamp to something readable
                DateFormat dateFormat = DateFormat.getDateInstance();
                String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_NAME))).getTime()); // Feb 23, 2020
                item.setDateItemAdded(formattedDate);

                itemList.add(item);
            } while (cursor.moveToNext());
        }
        return itemList;
    }

    //Todo: Add updateItem
    public int updateItem(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.KEY_BABY_ITEM, item.getItemName());
        contentValues.put(Constants.KEY_COLOR, item.getItemColor());
        contentValues.put(Constants.KEY_QTY_NUMBER, item.getItemQuantity());
        contentValues.put(Constants.KEY_ITEM_SIZE, item.getItemSize());
        contentValues.put(Constants.KEY_DATE_NAME, System.currentTimeMillis());
        int updated = db.update(Constants.TABLE_NAME,  contentValues,Constants.KEY_ID + " = ?", new String[]{String.valueOf(item.getId())});
        db.close();
        return updated;
    }

    //Todo: Add Delete Item
    public void deleteItem(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.KEY_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    //Todo: getItemCount
    public int getItemCount(){
        String countQuery = "SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int counts = cursor.getCount();
        cursor.close();
        return counts;
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(Constants.TABLE_NAME, null, null);
        db.close();
    }

}
