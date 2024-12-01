package com.example.shoppinglistapp

import android.util.Log
import androidx.lifecycle.LiveData

class GroceryModel {
    private val groceryListDao = MainActivity.groceryListDataBase.getGroceryListDao()
    fun addItem(id:Int, itemName:String, itemQuantity:Int){
        val temp=Item(id, itemName, itemQuantity)
       groceryListDao.addItem(temp)
    }
    fun deleteItem(groceryItemId: Int){
        groceryListDao.deleteItem(groceryItemId)
    }

    fun updateItem(id:Int, itemName:String, itemQuantity:Int){
        val temp=Item(id, itemName, itemQuantity)
        groceryListDao.updateItem(temp)
    }

    fun getItems(): LiveData<List<Item>> {
        return groceryListDao.getItems()
    }
}