package com.example.shoppinglistapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class GroceryListViewModel : ViewModel() {

    private val groceryModel:GroceryModel = GroceryModel()
    val groceryItems:LiveData<List<Item>>  = groceryModel.getItems()


    fun addItem(itemName:String, itemQuantity:Int){
        viewModelScope.launch {
            groceryModel.addItem(itemName, itemQuantity)
        }

    }
    fun deleteItem(groceryItemId: Int){
        viewModelScope.launch {
            groceryModel.deleteItem(groceryItemId)
        }
    }

    fun updateItem(groceryItemId:Int, groceryItemName:String, groceryItemQuantity:Int){
        viewModelScope.launch {
            groceryModel.updateItem(groceryItemId, groceryItemName, groceryItemQuantity)
        }
    }


}