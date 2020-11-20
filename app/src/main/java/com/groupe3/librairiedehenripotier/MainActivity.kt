package com.groupe3.librairiedehenripotier

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.groupe3.librairiedehenripotier.api.HenriPotierData
import com.groupe3.librairiedehenripotier.model.Book
import com.groupe3.librairiedehenripotier.presenter.BookListPresenter
import com.groupe3.librairiedehenripotier.utils.Contants.KEY_MOVIE_ID
import com.groupe3.librairiedehenripotier.view.BookDetailsActivity
import com.groupe3.librairiedehenripotier.view.View

class MainActivity : AppCompatActivity(), View {

    private lateinit var  recycler : RecyclerView
    private lateinit var  presenter : BookListPresenter
    private lateinit var listBooks : ArrayList<Book>
    private lateinit var recyclerAdapter : BookListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        this.recycler = findViewById(R.id.recycler_view_books)

        this.listBooks = ArrayList()

        this.recyclerAdapter = BookListAdapter(this, listBooks);

        recyclerAdapter.notifyDataSetChanged()

        recycler.adapter = recyclerAdapter;

        this.recycler.layoutManager = LinearLayoutManager(this)

        this.presenter = BookListPresenter(this, HenriPotierData)
        this.presenter.onResume()

    }

     override fun setDataRecyclerView(books: List<Book>) {

         listBooks.addAll(books)
         recyclerAdapter.notifyDataSetChanged()


    }

    fun onMovieItemClick(position: Int) {
        if (position == -1) {
            return
        }
        val detailIntent = Intent(this, BookDetailsActivity::class.java)
        detailIntent.putExtra(KEY_MOVIE_ID, position)
        startActivity(detailIntent)
    }
}