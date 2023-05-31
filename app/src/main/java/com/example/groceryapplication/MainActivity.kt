package com.example.groceryapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() , GroceryRVAdapter.GroceryItemClickInterface {


    lateinit var itemsRV : RecyclerView
    lateinit var addFAB : FloatingActionButton
    lateinit var list:List<GroceryItems>
    lateinit var groceryRVAdapter: GroceryRVAdapter
    lateinit var groceryViewModal: GroceryViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemsRV = findViewById(R.id.idRVItems)
        addFAB = findViewById(R.id.idFABAdd)
        list = ArrayList<GroceryItems>()
        groceryRVAdapter = GroceryRVAdapter(list,this)
        itemsRV.layoutManager = LinearLayoutManager(this)
        itemsRV.adapter = groceryRVAdapter
        val groceryRepository = GroceryRepository(GroceryDatabase(this))
        val factory = GroceryViewModalFactory(groceryRepository)
        groceryViewModal = ViewModelProvider(this,factory).get(GroceryViewModal::class.java)
        groceryViewModal.getAllGroceryItems().observe(this) {
            groceryRVAdapter.list = it
            groceryRVAdapter.notifyDataSetChanged()
        }
        addFAB.setOnClickListener {
        openDialog()

        }



    }

    private fun openDialog() {

    }

    override fun onItemClick(item: GroceryItems) {
    groceryViewModal.delete(item)
        groceryRVAdapter.notifyDataSetChanged()
        Toast.makeText(this,"Item Deleted",Toast.LENGTH_SHORT).show()
    }
}