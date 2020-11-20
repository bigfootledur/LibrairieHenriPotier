package com.groupe3.librairiedehenripotier.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.groupe3.librairiedehenripotier.R
import com.groupe3.librairiedehenripotier.api.HenriPotierData
import com.groupe3.librairiedehenripotier.model.Book
import com.groupe3.librairiedehenripotier.presenter.BookDetailsPresenter
import com.groupe3.librairiedehenripotier.utils.Contants.KEY_MOVIE_ID

class BookDetailsActivity  : AppCompatActivity(), View {

    private lateinit var  presenter : BookDetailsPresenter
    private lateinit var book : Book
    private var bookId : Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_details_activity)

        val mIntent = intent
        bookId = mIntent.getIntExtra(KEY_MOVIE_ID, 2)

        presenter = BookDetailsPresenter(this, HenriPotierData)
        presenter.onResume()
    }
    override fun setDataView(items: List<Book>) {
        if(items.isNotEmpty()){
            if(bookId.toInt()!=-1 && bookId.toInt() < items.size){
                book = items.get(bookId.toInt())

                // get
                val titleView: TextView = this.findViewById(R.id.book_detail_title)
                val priceView: TextView = this.findViewById(R.id.book_detail_price)
                val imgView: ImageView = this.findViewById(R.id.book_detail_image)
                val synopsis: TextView = this.findViewById(R.id.book_detail_synopsis)

                // set
                titleView.text = book.title
                priceView.text = book.price.toString()
                synopsis.text = book.synopsis[0]

                // loading album cover using Glide library
                Glide.with(this)
                    .load(book.cover)
                    .into(imgView)
            }
        }
    }

}