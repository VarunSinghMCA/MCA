package com.example.simplemaplistshoppingapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.simplemaplistshoppingapp.ui.theme.SimpleMapListShoppingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repository = ShoppingRepository()
        val factory = ShoppingViewModelFactory(repository)
        val viewModel: ShoppingViewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)

        setContent {
            SimpleMapListShoppingAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ShoppingHub(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ShoppingHub(viewModel: ShoppingViewModel, modifier: Modifier = Modifier) {
    val shoppingList by viewModel.shoppingList.collectAsState()
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }
    var itemPrice by remember { mutableStateOf("") }

    LazyColumn(modifier = modifier.fillMaxSize().padding(16.dp)) {
        item {
            Text(
                text = "Shopping Hub",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        item {
            Column {
                Text(text = "Item Name")
                OutlinedTextField(
                    value = itemName,
                    onValueChange = { itemName = it },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Item Quantity")
                OutlinedTextField(
                    value = itemQuantity,
                    onValueChange = { itemQuantity = it },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Item Price")
                OutlinedTextField(
                    value = itemPrice,
                    onValueChange = { itemPrice = it },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val qty = itemQuantity.toIntOrNull() ?: 0
                        val price = itemPrice.toDoubleOrNull() ?: 0.0
                        if (itemName.isNotBlank() && qty > 0 && price > 0) {
                            viewModel.addItem(ShoppingItem(itemName, qty, price))
                            itemName = ""
                            itemQuantity = ""
                            itemPrice = ""
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Add Item")
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Shopping List",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        items(shoppingList) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = item.itemName, style = MaterialTheme.typography.bodyLarge)
                    Text(
                        text = "Qty: ${item.itemQuantity} | Price: $${item.itemPrice}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Button(onClick = {
                    viewModel.removeItem(item)
                }) {
                    Text(text = "Remove")
                }
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleMapListShoppingAppTheme {
        ShoppingHub(viewModel = ShoppingViewModel(ShoppingRepository()), modifier = Modifier)
    }
}
