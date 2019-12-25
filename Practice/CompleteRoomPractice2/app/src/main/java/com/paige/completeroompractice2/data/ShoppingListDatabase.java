package com.paige.completeroompractice2.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.paige.completeroompractice2.model.ShoppingList;

@Database(entities = {ShoppingList.class}, version = 1)
public abstract class ShoppingListDatabase  extends RoomDatabase {

    private static volatile ShoppingListDatabase INSTANCE;
    public abstract ShoppingListDao shoppingListDao();

    public static ShoppingListDatabase getDatabase(Context context){
        if(INSTANCE == null){
            synchronized (ShoppingListDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context, ShoppingListDatabase.class, "shopping_list_db")
                            .addCallback(roomCallBack)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            //여기서 원하는 로직을 작성한다.
            new PopulateDBAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDBAsync extends AsyncTask<Void, Void, Void>{

        private ShoppingListDao shoppingListDao;

        private PopulateDBAsync(ShoppingListDatabase db) {
            shoppingListDao = db.shoppingListDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            shoppingListDao.deleteAllShoppingList();
            shoppingListDao.insertShoppingList(new ShoppingList("Item1", 3000));
            shoppingListDao.insertShoppingList(new ShoppingList("Item2", 4000));

            return null;
        }
    }

}
