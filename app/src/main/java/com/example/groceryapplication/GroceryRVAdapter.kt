package com.example.groceryapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class GroceryRVAdapter(
    var list: List<GroceryItems>,
     val groceryItemClickInterface: GroceryItemClickInterface
) : RecyclerView.Adapter<GroceryRVAdapter.GroceryViewHolder>() {

inner class GroceryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
val nameTV = itemView.findViewById<TextView>(R.id.idTVItemName)
val quantityTV = itemView.findViewById<TextView>(R.id.idTVQuantity)
    val rateTV = itemView.findViewById<TextView>(R.id.idTVRate)
    val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)
    val amountTV = itemView.findViewById<TextView>(R.id.idTVTotalAmt)

}




    interface GroceryItemClickInterface{
        fun onItemClick(item: GroceryItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grocery_rv_item,parent,false)
        return GroceryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        holder.nameTV.text = list[position].itemName
        holder.quantityTV.text = list.get(position).itemQuantity.toString()
        holder.rateTV.text = "Rs. "+list.get(position).itemPrice.toString()
        val itemTotal :Double = list.get(position).itemPrice * list.get(position).itemQuantity
        holder.amountTV.text = "Rs. "+itemTotal.toString()
        holder.deleteIV.setOnClickListener{
            groceryItemClickInterface.onItemClick(list[position])
        }


    }

}