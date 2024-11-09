package com.example.shoppinglistapp

class GroceryModel {
    private var groceryItems:MutableList<Item> = mutableListOf()
    fun addItem(id:Int, itemName:String, itemQuantity:Int){
        val temp=Item(id, itemName, itemQuantity)
        groceryItems.add(temp)
    }
    fun getItems():MutableList<Item>{
        return groceryItems
    }
}