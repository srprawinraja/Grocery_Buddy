package com.example.shoppinglistapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GroceryListViewModel : ViewModel() {
    private var _groceryItems = MutableLiveData<List<Item>>()
    val groceryItems:LiveData<List<Item>>  = _groceryItems
    private val groceryModel:GroceryModel = GroceryModel()

    fun addItem(id:Int, itemName:String, itemQuantity:Int){
        groceryModel.addItem(id, itemName, itemQuantity)
        _groceryItems.value=groceryModel.getItems().toList()
    }
    fun deleteItem(groceryItemId: Int){
        groceryModel.deleteItem(groceryItemId)
        _groceryItems.value=groceryModel.getItems().toList()
    }

    fun updateItem(groceryItemId:Int, groceryItemName:String, groceryItemQuantity:Int){
        groceryModel.updateItem(groceryItemId, groceryItemName, groceryItemQuantity)
        _groceryItems.value=groceryModel.getItems().toList()
    }


}