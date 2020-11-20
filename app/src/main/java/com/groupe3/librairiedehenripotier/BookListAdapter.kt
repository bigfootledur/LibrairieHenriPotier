package com.groupe3.librairiedehenripotier

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.groupe3.librairiedehenripotier.model.Book

class BookListAdapter(private val books: List<Book>) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleView: TextView = itemView.findViewById(R.id.book_title_item)
        val priceView: TextView = itemView.findViewById(R.id.book_price_item)
        val imgView: ImageView  = itemView.findViewById(R.id.book_image_item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.adapter_item, parent, false)
        // Return a new holder instance
        return BookViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        var currentItem:Book = books.get(position)

        val title:String = currentItem.title
        val price:Float = currentItem.price
        val cover:String = currentItem.cover

        holder.titleView.text = title
        holder.priceView.text = price.toString() + "€"
    }

    override fun getItemCount(): Int {
        return books.size
    }
}