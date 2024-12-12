package com.example.shoppinglistapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.shoppinglistapp.Item
@Dao
interface GroceryListDao {

    @Query("SELECT * FROM Item")
    fun getItems() : LiveData<List<Item>>

    @Insert
    suspend fun addItem(item:Item)

    @Query("DELETE FROM Item WHERE id=:id")
    suspend fun deleteItem(id:Int)

    @Query("UPDATE Item SET name = :name, quantity = :quantity WHERE id = :id")
    suspend fun updateItem(id:Int, name:String, quantity:Int)



}