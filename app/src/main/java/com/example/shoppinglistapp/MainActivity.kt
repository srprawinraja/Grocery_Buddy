package com.example.shoppinglistapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppinglistapp.db.GroceryListDao
import com.example.shoppinglistapp.db.GroceryListDataBase
import com.example.shoppinglistapp.ui.theme.ShoppingListAppTheme

class MainActivity : ComponentActivity() {
    companion object{
        lateinit var groceryListDataBase: GroceryListDataBase
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Room.databaseBuilder(
            applicationContext,
            GroceryListDataBase::class.java,
            GroceryListDataBase.NAME
        ).build()
        setContent {
            ShoppingListAppTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    // Declare state variables and context

                    val context = LocalContext.current                     // Get the current context
                    val groceryListViewModel : GroceryListViewModel by viewModels()

                    View(groceryListViewModel, context)
                }
            }
        }
    }
}



@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun ViewPreview() {
    ShoppingListAppTheme {
        View(
            groceryListViewModel =  GroceryListViewModel(),
            context = LocalContext.current
            )
    }
}