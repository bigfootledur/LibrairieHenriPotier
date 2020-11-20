package com.groupe3.librairiedehenripotier

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.groupe3.librairiedehenripotier.model.Book

class BookListAdapter(private var books: List<Book>) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

    inner class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.book_title_item)
        val priceView: TextView = view.findViewById(R.id.book_price_item)
        val imgView: ImageView  = view.findViewById(R.id.book_image_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // Inflate the custom layout
        val bookView = inflater.inflate(R.layout.adapter_item, parent, false)
        // Return a new holder instance
        return BookViewHolder(bookView)
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