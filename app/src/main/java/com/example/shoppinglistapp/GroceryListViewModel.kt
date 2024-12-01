package com.example.shoppinglistapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GroceryListViewModel : ViewModel() {

    private val groceryModel:GroceryModel = GroceryModel()

    val groceryItems:LiveData<List<Item>>  = groceryModel.getItems()

    fun addItem(id:Int, itemName:String, itemQuantity:Int){
        groceryModel.addItem(id, itemName, itemQuantity)
    }
    fun deleteItem(groceryItemId: Int){
        groceryModel.deleteItem(groceryItemId)
    }

    fun updateItem(groceryItemId:Int, groceryItemName:String, groceryItemQuantity:Int){
        groceryModel.updateItem(groceryItemId, groceryItemName, groceryItemQuantity)
    }


}