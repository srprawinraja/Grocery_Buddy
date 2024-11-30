package com.example.shoppinglistapp

import android.util.Log

class GroceryModel {
    private var groceryItems:MutableList<Item> = mutableListOf()
    fun addItem(id:Int, itemName:String, itemQuantity:Int){
        val temp=Item(id, itemName, itemQuantity)
        groceryItems.add(temp)
    }
    fun deleteItem(groceryItemId: Int){
        var currentId = groceryItemId
        for(items in  groceryItems){
            if(items.id==groceryItemId && currentId == groceryItemId){
                groceryItems.removeAt(groceryItemId-1)
                currentId+=1
            } else{
                items.id=currentId
                currentId+=1
            }

        }
    }

    fun updateItem(groceryItemId:Int, groceryItemName:String, groceryItemQuantity:Int){
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