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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SwipeToDismiss
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppinglistapp.ui.theme.ShoppingListAppTheme


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun View(
    groceryListViewModel: GroceryListViewModel,
    context: Context
) {
    val showDialogForAdd = remember { mutableStateOf(false) }  // State for dialog visibility
    val showDialogForUpdate = remember { mutableStateOf(false) }  // State for dialog visibility
    val currItemName= remember { mutableStateOf("") }
    val currItemVal= remember{mutableStateOf("")}
    val currItemId = remember{ mutableStateOf(0) }
    val groceryItems = groceryListViewModel.groceryItems.observeAsState(emptyList()).value

    if(showDialogForAdd.value) {
        ShowDialog(
            groceryListViewModel ,context, showDialogForAdd, currItemId,
            currItemName, currItemVal,
            "Add Item", "Add"
        )
    }

    Column (
        modifier = Modifier.fillMaxSize().background(color = Color.White)
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
                showDialogForAdd.value=true
                currItemId.value=groceryItems.size
                currItemName.value=""
                currItemVal.value=""
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
                if(showDialogForUpdate.value) {
                    ShowDialog(
                        groceryListViewModel,
                        context,
                        showDialogForUpdate,
                        currItemId,
                        currItemName,
                        currItemVal,
                        "Update Item",
                        "Update"
                    )
                }
                val dismissState = rememberDismissState(
                    confirmValueChange = {dismissValue->
                        if(dismissValue==DismissValue.DismissedToStart){
                            groceryListViewModel.deleteItem(groceryItem.id)
                        } else if(dismissValue == DismissValue.DismissedToEnd){
                            showDialogForUpdate.value=true
                            currItemId.value=groceryItem.id
                            currItemName.value=groceryItem.name
                            currItemVal.value=groceryItem.quantity.toString()
                        }
                        false
                    }
                )

                val swipeDirection = remember {
                    mutableStateOf(false)
                }
                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart),
                    background = {

                                 val animeColor = animateColorAsState(
                                     targetValue = if(dismissState.dismissDirection == DismissDirection.EndToStart){
                                         swipeDirection.value=true
                                         Color.Red
                                     } else {
                                         swipeDirection.value=false
                                         Color.Blue
                                     },
                                     label = ""
                                 )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 10.dp, top = 10.dp, end = 10.dp)
                                .border(
                                    if (swipeDirection.value) {
                                        BorderStroke(1.dp, color = Color.Red)
                                    } else BorderStroke(1.dp, color = Color.Blue),
                                    shape = RoundedCornerShape(30)
                                )
                                .background(animeColor.value),
                            contentAlignment = if(swipeDirection.value) Alignment.CenterEnd else Alignment.CenterStart
                            ){

                            Icon(imageVector = if (swipeDirection.value) Icons.Default.Delete else Icons.Default.Edit, contentDescription = "delete", tint = Color.Black,

                                modifier = Modifier.padding(end = 20.dp, start = 20.dp))
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
            )
            .background(Color.White),

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
                    ),
                    color = Color.Black
                )
            }
        }

    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ShowDialog(
    groceryListViewModel: GroceryListViewModel,
    context: Context,
    showDialog: MutableState<Boolean>,
    currItemId: MutableState<Int>,
    currItemName: MutableState<String>,
    currItemVal: MutableState<String>,
    titleText: String,
    buttonText: String
) {

 // if(showDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showDialog.value = false
            },
            title = {
                Text(text = titleText)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog.value = false
                        if(!currItemName.value.equals("") && !currItemVal.value.equals("")) {
                            try {
                                if(buttonText=="Add"){
                                    groceryListViewModel.addItem(
                                        currItemId.value,
                                        currItemName.value.trim(), currItemVal.value.trim().toInt()
                                    )
                                } else{

                                    groceryListViewModel.updateItem(
                                        currItemId.value,
                                        currItemName.value,
                                        currItemVal.value.toInt()
                                    )

                                }
                            } catch(ex:NumberFormatException){
                                Toast.makeText(context, "please enter number in quantity box", Toast.LENGTH_SHORT).show()
                            }

                        } else{
                            Toast.makeText(context, "Fill the Box", Toast.LENGTH_SHORT).show()
                        }
                    },

                    ) {
                    Text(text = buttonText)
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
 // }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun DisplayItemsPreview() {
    ShoppingListAppTheme {
        DisplayItems(name = "apple", quantity ="2" )
    }
}

