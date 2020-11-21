package com.groupe3.librairiedehenripotier.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.groupe3.librairiedehenripotier.MainActivity
import com.groupe3.librairiedehenripotier.R
import com.groupe3.librairiedehenripotier.model.Book


class BookListAdapter(private val bookActivity: MainActivity, private var books: List<Book>) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

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


        // loading album cover using Glide library
        Glide.with(bookActivity)
            .load(cover)
            .into(holder.imgView)


        holder.itemView.setOnClickListener { bookActivity.onMovieItemClick(position) }
    }

    override fun getItemCount(): Int {
        return books.size
    }
}