package com.example.shoppinglistapp

import android.util.Log
import androidx.lifecycle.LiveData

class GroceryModel {
    private val groceryListDao = MainActivity.groceryListDataBase.getGroceryListDao()
    suspend fun addItem(itemName: String, itemQuantity: Int) {
        groceryListDao.addItem(Item(name = itemName, quantity = itemQuantity))
    }

    suspend fun deleteItem(id: Int) {
        groceryListDao.deleteItem(id)
    }

    suspend fun updateItem(itemId: Int, itemName: String, itemQuantity: Int) {
        groceryListDao.updateItem(itemId, itemName, itemQuantity)
    }
    fun getItems(): LiveData<List<Item>> {
        return groceryListDao.getItems()
    }


}