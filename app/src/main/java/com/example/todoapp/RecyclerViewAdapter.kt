package com.example.todoapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class RecyclerViewAdapter( val  items: ArrayList<ToDoItem>
):RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,parent,false

            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]

        holder.itemView.apply {
            textView.text = item.text
            checkBox.isChecked = item.checked
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    textView.setTextColor(Color.GRAY)
                }else{
                    textView.setTextColor(Color.BLACK)
                }
                item.checked = !item.checked
            }
            }

        }
        override fun getItemCount() = items.size
    fun deleteItems() {
        items.removeAll{ item -> item.checked }
        notifyDataSetChanged()
    }

}