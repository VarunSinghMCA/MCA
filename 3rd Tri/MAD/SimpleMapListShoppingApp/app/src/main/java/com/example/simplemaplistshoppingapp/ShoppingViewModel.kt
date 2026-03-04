package com.example.simplemaplistshoppingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.StateFlow

class ShoppingViewModel(
    private val repository: ShoppingRepository
) : ViewModel() {
    val shoppingList: StateFlow<List<ShoppingItem>> = repository.shoppingList
    fun addItem(item: ShoppingItem) {
        repository.addItem(item)
    }

    fun removeItem(item: ShoppingItem) {
        repository.removeItem(item)
    }
}
class ShoppingViewModelFactory(private val repository: ShoppingRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShoppingViewModel(repository) as T
    }
}

