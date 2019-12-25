package com.paige.completeroompractice2.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.paige.completeroompractice2.util.ShoppingListRepository;

import java.util.List;

public class ShoppingListViewModel extends AndroidViewModel {

    private ShoppingListRepository shoppingListRepository;
    private LiveData<List<ShoppingList>> shoppingLists;

    public ShoppingListViewModel(@NonNull Application application) {
        super(application);
        shoppingListRepository = new ShoppingListRepository(application);
        shoppingLists = shoppingListRepository.getAllShippingList();
    }

    public void insertOneShoppingList(ShoppingList shoppingList){
        shoppingListRepository.insertOneShoppingList(shoppingList);
    }

    public LiveData<List<ShoppingList>> getAllShoppingList(){
        return shoppingLists;
    }


}
