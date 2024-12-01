package com.example.shoppinglistapp

import android.util.Log

class GroceryModel {
    private var groceryItems:MutableList<Item> = mutableListOf()
    fun addItem(id:Int, itemName:String, itemQuantity:Int){
        Log.i("value add", id.toString())

        val temp=Item(id, itemName, itemQuantity)
        groceryItems.add(temp)
    }
    fun deleteItem(groceryItemId: Int){

        var currentIndex=groceryItemId
        val size = groceryItems.size
        for(items in  groceryItems){
            if(items.id==currentIndex && groceryItems.size == size){
                Log.i("value delete", currentIndex.toString())
                groceryItems.removeAt(currentIndex)
            } else if( groceryItems.size != size){
                items.id = currentIndex-1
                currentIndex+=1
            }

        }
    }

    fun updateItem(groceryItemId:Int, groceryItemName:String, groceryItemQuantity:Int){
        Log.i("value update", groceryItemId.toString())
        for(items in groceryItems){
            if(items.id==groceryItemId){
                items.name=groceryItemName
                items.quantity=groceryItemQuantity
            }
        }
    }

    fun getItems():MutableList<Item>{
        return groceryItems
    }
}