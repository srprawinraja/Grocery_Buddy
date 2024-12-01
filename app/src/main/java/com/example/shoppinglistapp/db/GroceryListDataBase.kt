package com.example.shoppinglistapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shoppinglistapp.Item

@Database(entities = [Item::class], version = 1)
abstract class GroceryListDataBase: RoomDatabase() {
    companion object{
        const val NAME="GroceryList_DB"
    }
    abstract fun getGroceryListDao(): GroceryListDao


}