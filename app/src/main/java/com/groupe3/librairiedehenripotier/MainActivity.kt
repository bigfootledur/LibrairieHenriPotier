package com.groupe3.librairiedehenripotier

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.groupe3.librairiedehenripotier.api.HenriPotierData
import com.groupe3.librairiedehenripotier.model.Book
import com.groupe3.librairiedehenripotier.presenter.BookListPresenter
import com.groupe3.librairiedehenripotier.view.View

class MainActivity : AppCompatActivity(), View {

    private lateinit var  recycler : RecyclerView
    private lateinit var  presenter : BookListPresenter
    private lateinit var listBooks : ArrayList<Book>
    private lateinit var recyclerAdapter : BookListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        this.listBooks = ArrayList()
        this.listBooks.add(Book("title 1", "", 1.5.toFloat(), "img"))
        this.recycler = findViewById(R.id.recycler_view_books)
        this.presenter = BookListPresenter(this, HenriPotierData)
        this.recyclerAdapter = BookListAdapter(listBooks);
        recycler.adapter = recyclerAdapter;

        // Lookup the recyclerview in activity layout
        this.recycler = findViewById(R.id.recycler_view_books)

        // Create adapter passing in the sample user data
        val adapter = BookListAdapter(listBooks)
        // Attach the adapter to the recyclerview to populate items
        this.recycler.adapter = adapter
        // Set layout manager to position the items
        this.recycler.layoutManager = LinearLayoutManager(this)
    }

     override fun setDataRecyclerView(books: ArrayList<Book>) {
        listBooks = books;
        println(books);
    }
}