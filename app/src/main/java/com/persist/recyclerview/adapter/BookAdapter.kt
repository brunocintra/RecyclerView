package com.persist.recyclerview.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.persist.recyclerview.R
import kotlinx.android.synthetic.main.activity_main_list_item.view.*

class BookAdapter (nameList: List<String>, internal var ctx: Context): RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    internal var nameList: List<String> = ArrayList<String>()
    init {
        this.nameList = nameList
    }

    // Aqui é onde o viewholder é criado a partir do layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.activity_main_list_item, parent, false)
        return ViewHolder(view)
    }

    // Nessa parte é onde se modifica o item do viewholder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title = nameList[position]

        holder.itemView.txtTitle.text = title
    }

    // Devolve quantidade de itens do nameList
    override fun getItemCount(): Int {
        return nameList.size
    }

    // Aqui é a criação dos itens do viewholder
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var title = view.txtTitle
    }
}