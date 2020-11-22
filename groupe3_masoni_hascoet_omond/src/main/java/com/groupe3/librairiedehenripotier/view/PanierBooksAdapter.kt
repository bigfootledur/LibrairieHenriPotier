package com.groupe3.librairiedehenripotier.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.groupe3.librairiedehenripotier.R
import com.groupe3.librairiedehenripotier.model.Book

class PanierBooksAdapter(private val panierActivity: PanierActivity, private val books: ArrayList<Pair<Book, Int>>): RecyclerView.Adapter<PanierBooksAdapter.PanierBookViewHolder>() {
    inner class PanierBookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleBookView: TextView = view.findViewById(R.id.panier_book_title)
        val priceBookView: TextView = view.findViewById(R.id.panier_book_price)
        val quantityBookView: TextView = view.findViewById(R.id.panier_book_quantity)
        val deleteButton: Button = view.findViewById(R.id.delete_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PanierBooksAdapter.PanierBookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val bookView = inflater.inflate(R.layout.book_panier_adapter, parent, false)
        return PanierBookViewHolder(bookView)
    }

    override fun onBindViewHolder(holder: PanierBooksAdapter.PanierBookViewHolder, position: Int) {
        val (currentBook, quantity) = books[position]

        holder.titleBookView.text = currentBook.title
        holder.priceBookView.text = holder.itemView.context.getString(
                R.string.montant_prix, currentBook.price.toString()
        )
        holder.quantityBookView.text = holder.itemView.context.getString(
                R.string.quantity_book, quantity.toString()
        )
        holder.deleteButton.setOnClickListener {
            val newQuantity = quantity - 1
            books[position] = Pair(currentBook, newQuantity)
            if(newQuantity <= 0) {
                books.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, books.size)
            }
            notifyDataSetChanged()
            panierActivity.removeBook(currentBook)
        }
    }

    override fun getItemCount(): Int {
        return books.size
    }
}