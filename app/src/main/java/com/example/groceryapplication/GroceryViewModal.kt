package com.example.groceryapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GroceryViewModal(private val repository: GroceryRepository) : ViewModel() {

    fun insert(item: GroceryItems) = GlobalScope.launch {

        repository.insert(item)
    }

    fun delete(item: GroceryItems) = GlobalScope.launch {
        repository.delete(item)
    }

    fun getAllGroceryItems() = repository.getAllItems()
}