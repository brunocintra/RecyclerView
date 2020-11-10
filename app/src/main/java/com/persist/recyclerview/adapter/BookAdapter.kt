package com.persist.recyclerview.adapter

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.persist.recyclerview.R
import com.persist.recyclerview.domain.Book
import kotlinx.android.synthetic.main.activity_main_list_item.view.*

class BookAdapter (books: List<Book>,
                   internal var ctx: Context,
                   private val callback: (Book) -> Unit): RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    internal var bookList: List<Book> = ArrayList<Book>()
    init { this.bookList = books  }

    private val favoriteImages: TypedArray by lazy {
        ctx.resources.obtainTypedArray(R.array.favorite)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main_list_item, parent, false)
        val viewHolder = ViewHolder(view)

        viewHolder.itemView.setOnClickListener {
            val book = bookList[viewHolder.adapterPosition]
            callback(book)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = bookList[position]

        holder.itemView.txtTitle.text = book.title
        holder.itemView.txtSubtitle.text = book.subtitle
        holder.itemView.imgViewFavorite?.setImageDrawable(favoriteImages.getDrawable(book.favorite))
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var title = view.txtTitle
        var subtitle = view.txtSubtitle
        val favorite = view.imgViewFavorite
    }

}