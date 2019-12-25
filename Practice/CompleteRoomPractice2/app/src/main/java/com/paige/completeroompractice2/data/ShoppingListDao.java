package com.paige.completeroompractice2.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.paige.completeroompractice2.model.ShoppingList;

import java.util.List;

@Dao
public interface ShoppingListDao {

    //CRUDE MODEL
    @Insert
    void insertShoppingList(ShoppingList shoppingList);

    @Query("SELECT * FROM shopping_list")
    LiveData<List<ShoppingList>> getAllShoppingList();

    @Query("SELECT * FROM shopping_list WHERE id = :id AND name = :name")
    ShoppingList getOneShoppingList(int id, String name);

    @Query("UPDATE shopping_list SET name = :name AND price = :price WHERE id = :id")
    int updateOneShoppingList(int id, String name, long price);

    @Query("DELETE FROM shopping_list WHERE id = :id")
    int deleteOneShoppingList(int id);

    @Query("DELETE FROM shopping_list")
    void deleteAllShoppingList();

}
