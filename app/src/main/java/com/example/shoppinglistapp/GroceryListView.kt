package com.example.shoppinglistapp

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.SwipeToDismissDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppinglistapp.ui.theme.ShoppingListAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun View(
    groceryListViewModel: GroceryListViewModel,
    context: Context
) {
    val showDialog = remember { mutableStateOf(false) }  // State for dialog visibility
    val groceryItems = groceryListViewModel.groceryItems.observeAsState(emptyList()).value

    ShowDialog(groceryListViewModel, groceryItems, context, showDialog)
    Column (
        modifier = Modifier.fillMaxSize()
    ){
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Grocery Buddy",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                showDialog.value=true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(imageVector = Icons.Default.Add,
                    contentDescription = "add",
                    tint = Color.White,)
                Text(text = "Add Items", modifier = Modifier.padding(15.dp), color = Color.White)
            }
        }
        LazyColumn (
            modifier = Modifier.fillMaxSize()
        ){
            items(groceryItems, key = {groceryItem -> groceryItem.id}){
                groceryItem->
                val dismissState = rememberDismissState(
                    confirmValueChange = {dismissValue->
                        if(dismissValue==DismissValue.DismissedToStart){
                            groceryListViewModel.deleteItem(groceryItem)
                        } else if(dismissValue == DismissValue.DismissedToEnd){

                        }
                        true
                    },

                )
                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.EndToStart),
                    background = {

                                 val animeColor = animateColorAsState(
                                     targetValue = if(dismissState.dismissDirection == DismissDirection.EndToStart){
                                         Color.Red
                                     } else Color.Transparent,
                                     label = ""
                                 )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 10.dp, top = 10.dp, end = 10.dp)
                                .border(
                                    BorderStroke(1.dp, color = Color.Red),
                                    shape = RoundedCornerShape(30)
                                ).background(animeColor.value),
                            contentAlignment = Alignment.CenterEnd
                            ){
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "delete", tint = Color.Black,
                                modifier = Modifier.padding(end = 10.dp))
                        }
                    },
                    dismissContent = {
                        DisplayItems(name =groceryItem.name , quantity = groceryItem.quantity.toString())
                    }
                )
            }
        }

    }
}
@Composable
fun DisplayItems(name: String, quantity: String) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 10.dp, end = 10.dp)
            .clickable {

            }
            .border(
                BorderStroke(1.dp, colorResource(id = R.color.silver)),
                shape = RoundedCornerShape(30)
            ).background(Color.White),

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 40.dp, bottom = 5.dp, top = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = name,
                modifier = Modifier.padding(15.dp),
                color = Color.Black
            )
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .border(
                        BorderStroke(2.dp, Color.Green),
                        shape = RoundedCornerShape(100)
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally


            ) {
                Text(
                    text = quantity,
                    modifier = Modifier.padding(
                        start = 15.dp,
                        bottom = 10.dp,
                        end = 15.dp,
                        top = 10.dp
                    )
                )
            }
        }

    }
}

@Composable
fun ShowDialog(
    groceryListViewModel: GroceryListViewModel,
    groceryItems: List<Item>,
    context: Context,
    showDialog: MutableState<Boolean>,
    ) {
    val currItemName = remember { mutableStateOf("") }  // State for current item name
    val currItemVal = remember { mutableStateOf("") }   // State for current item quantity
  if(showDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showDialog.value = false
            },
            title = {
                Text(text = "Add Item")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog.value = false
                        if(!currItemName.equals("") && !currItemVal.equals("")) {
                            try {
                                groceryListViewModel.addItem(
                                    groceryItems.size + 1,
                                    currItemName.value, currItemVal.value.toInt()
                                )
                                Log.i("display the value", groceryListViewModel.groceryItems.value.toString())
                                currItemVal.value = ""
                                currItemName.value = ""
                            } catch(ex:NumberFormatException){
                                Toast.makeText(context, "please enter number in quantity box", Toast.LENGTH_SHORT).show()
                            }

                        } else{
                            Toast.makeText(context, "Fill the Box", Toast.LENGTH_SHORT).show()
                        }
                    },

                    ) {
                    Text(text = "Add")
                }
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = currItemName.value,
                        onValueChange = {newValue->
                            currItemName.value=newValue
                        },
                        placeholder = {
                            Text(text = "Enter the Item Name")
                        }
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    OutlinedTextField(
                        value = currItemVal.value,
                        onValueChange = {newValue->
                            currItemVal.value=newValue
                        },
                        placeholder = {
                            Text(text = "Enter the Item Quantity")
                        })
                }
            }
        )
  }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun DisplayItemsPreview() {
    ShoppingListAppTheme {
        DisplayItems(name = "apple", quantity ="2" )
    }
}

