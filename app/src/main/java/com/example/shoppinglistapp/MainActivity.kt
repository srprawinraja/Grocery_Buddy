package com.example.shoppinglistapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppinglistapp.ui.theme.ShoppingListAppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListAppTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    // Declare state variables and context
                    val currItemName = remember { mutableStateOf("") }  // State for current item name
                    val currItemVal = remember { mutableStateOf("") }   // State for current item quantity
                    val showDialog = remember { mutableStateOf(false) }  // State for dialog visibility
                    var itemList =  mutableStateListOf<Item>()                  // Mutable list to hold grocery items
                    val context = LocalContext.current                     // Get the current context

                    // Call the View function with the declared variables
                    // Passes the current item name, quantity, dialog state, item list, and context to the View
                    // Implemented in next step
                    View(currItemName, currItemVal, showDialog, itemList, context)
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
        val currItemName = remember { mutableStateOf("Sample Item") }
        val currItemVal = remember { mutableStateOf("10") }
        val showDialog = remember { mutableStateOf(false) }

        View(
            currItemName = currItemName,
            currItemVal = currItemVal,
            showDialog = showDialog,
            itemList = mutableStateListOf<Item>(),
            current = LocalContext.current,
        )
    }
}