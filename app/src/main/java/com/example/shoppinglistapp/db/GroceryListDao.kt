package com.example.shoppinglistapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.shoppinglistapp.Item
@Dao
interface GroceryListDao {

    @Query("SELECT * FROM Item")
    fun getItems() : LiveData<List<Item>>

    @Insert
    fun addItem(item:Item)

    @Query("DELETE FROM Item where id=:id")
    fun deleteItem(id:Int)

    @Update
    fun updateItem(item:Item)

}