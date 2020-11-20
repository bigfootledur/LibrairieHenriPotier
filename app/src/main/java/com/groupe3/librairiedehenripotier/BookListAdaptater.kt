package com.groupe3.librairiedehenripotier

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.groupe3.librairiedehenripotier.model.Book

class BookListAdaptater (private val context:Context, private val bookItemList:List<Book>, private val inflater: LayoutInflater = LayoutInflater.from(context)) : BaseAdapter() {
    override fun getCount(): Int {
        return this.bookItemList.size
    }

    override fun getItem(position: Int): Book {
        return this.bookItemList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = inflater.inflate(R.layout.adapter_item,null)

        var currentItem:Book = getItem(position)
        val title:String = currentItem.title
        val price:Float = currentItem.price
        val urlImg:String = currentItem.cover

        val titleView: TextView = view.findViewById(R.id.book_title_item)
        val priceView: TextView = view.findViewById(R.id.book_price_item)
        val imgView: ImageView = view.findViewById(R.id.book_image_item)

        titleView.text = title
        priceView.text = price.toString() + "€"

        //imgView.setImageURI(Uri.("https://upload.wikimedia.org/wikipedia/commons/b/b1/Male_mallard_standing.jpg"))

        // on click on item
        view.setOnClickListener {
            Toast.makeText(context, "Hello", Toast.LENGTH_LONG).show()
        }

        return view
    }

}