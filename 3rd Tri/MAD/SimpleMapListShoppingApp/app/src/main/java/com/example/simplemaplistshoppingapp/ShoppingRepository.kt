package com.example.simplemaplistshoppingapp

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
class ShoppingRepository {
    private val _shoppingList = MutableStateFlow<List<ShoppingItem>>(emptyList())
    val shoppingList: StateFlow<List<ShoppingItem>> = _shoppingList

    fun addItem(item: ShoppingItem) {
        val currentList = _shoppingList.value.toMutableList()
        currentList.add(item)
        _shoppingList.value = currentList
    }

    fun removeItem(item: ShoppingItem) {
        val currentList = _shoppingList.value.toMutableList()
        currentList.remove(item)
        _shoppingList.value = currentList
    }
}