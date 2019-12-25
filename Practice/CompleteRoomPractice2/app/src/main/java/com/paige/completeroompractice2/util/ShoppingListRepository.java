package com.paige.completeroompractice2.util;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.paige.completeroompractice2.data.ShoppingListDao;
import com.paige.completeroompractice2.data.ShoppingListDatabase;
import com.paige.completeroompractice2.model.ShoppingList;

import java.util.List;


public class ShoppingListRepository {

    //Dao를 가져옴
    private ShoppingListDao shoppingListDao;
    private LiveData<List<ShoppingList>> shoppingLists;

    public ShoppingListRepository(Application application) {
        ShoppingListDatabase db = ShoppingListDatabase.getDatabase(application);
        shoppingListDao = db.shoppingListDao();
        shoppingLists = shoppingListDao.getAllShoppingList();
    }

    public void insertOneShoppingList(ShoppingList shoppingList){
        new InsertShoppingListAsyncTask(shoppingListDao).execute(shoppingList);
    }

    public LiveData<List<ShoppingList>> getAllShippingList(){
        return shoppingLists;
    }

    private static class InsertShoppingListAsyncTask extends AsyncTask<ShoppingList, Void, Void>{

        private ShoppingListDao shoppingListDao;

        private InsertShoppingListAsyncTask(ShoppingListDao shoppingListDao) {
            this.shoppingListDao = shoppingListDao;
        }

        @Override
        protected Void doInBackground(ShoppingList... shoppingLists) {
            shoppingListDao.insertShoppingList(shoppingLists[0]);
            return null;
        }
    }

}
