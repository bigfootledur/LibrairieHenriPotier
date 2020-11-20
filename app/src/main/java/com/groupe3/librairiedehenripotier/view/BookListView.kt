package com.groupe3.librairiedehenripotier.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.groupe3.librairiedehenripotier.R
import com.groupe3.librairiedehenripotier.api.HenriPotierAPI
import com.groupe3.librairiedehenripotier.model.Book
import com.groupe3.librairiedehenripotier.presenter.BookListPresenter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BookListView() : AppCompatActivity(), View {

   //private val presenter : BookListPresenter =
    private lateinit var  recycler : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        this.recycler = findViewById(R.id.recycler_view_books)
    }

    override fun setDataRecyclerView(items: List<Book>) {
        // init adapter ... ?
        // this.recycler.adapter =
        //items.adapter =

        println(items);
    }

}