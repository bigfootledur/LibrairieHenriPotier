package com.groupe3.librairiedehenripotier

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.groupe3.librairiedehenripotier.api.HenriPotierData
import com.groupe3.librairiedehenripotier.model.Book
import com.groupe3.librairiedehenripotier.presenter.BookListPresenter
import com.groupe3.librairiedehenripotier.presenter.PanierContent
import com.groupe3.librairiedehenripotier.utils.Contants.KEY_MOVIE_ID
import com.groupe3.librairiedehenripotier.view.BookDetailsActivity
import com.groupe3.librairiedehenripotier.view.PanierActivity
import com.groupe3.librairiedehenripotier.view.BookListView

class MainActivity : AppCompatActivity(), BookListView {

    private lateinit var  recycler : RecyclerView
    private lateinit var  presenter : BookListPresenter
    private lateinit var listBooks : ArrayList<Book>
    private lateinit var recyclerAdapter : BookListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.library_activity)
        setNavigationListener()

        this.recycler = findViewById(R.id.recycler_view_books)
        this.listBooks = ArrayList()
        this.recyclerAdapter = BookListAdapter(this, listBooks)
        recyclerAdapter.notifyDataSetChanged()
        recycler.adapter = recyclerAdapter
        this.recycler.layoutManager = LinearLayoutManager(this)

        this.presenter = BookListPresenter(this, HenriPotierData)
        this.presenter.onResume()
    }

     override fun setDataView(items: List<Book>) {
         listBooks.addAll(items)
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

    private fun setNavigationListener() {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.bottom_navigation_menu2 -> {
                    val panierIntent = Intent(this, PanierActivity::class.java)
                    startActivity(panierIntent)
                }
            }
            true
        }

        if(PanierContent.getBooks().isNotEmpty()) {
            val badge = bottomNavigation.getOrCreateBadge(R.id.bottom_navigation_menu2)
            badge.isVisible = true
            badge.number = PanierContent.getBooks().size
        }
    }
}