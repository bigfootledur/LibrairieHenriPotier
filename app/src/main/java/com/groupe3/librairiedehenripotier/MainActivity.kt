package com.groupe3.librairiedehenripotier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.groupe3.librairiedehenripotier.api.HenriPotierData
import com.groupe3.librairiedehenripotier.model.Book
import com.groupe3.librairiedehenripotier.presenter.BookListPresenter
import com.groupe3.librairiedehenripotier.utils.OfferCalculator
import com.groupe3.librairiedehenripotier.view.View
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), View {

    private lateinit var  listview : ListView
    private lateinit var  presenter : BookListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        this.listview = findViewById(R.id.listItems)
        this.presenter = BookListPresenter(this,HenriPotierData)

        GlobalScope.launch(Dispatchers.Main) {

            val listBooks = presenter.getBookList()

            val offers = HenriPotierData.commercialOffers(listBooks.map { book -> book.isbn })

            val bestOfferWithPrice = OfferCalculator.getBestOfferWithPrice(listBooks, offers)

            listview.adapter = BookListAdaptater(applicationContext,listBooks);
        }
        //this.listview.adapter = BookListAdaptater(this,listBooks);

        //this.presenter.getBookList()
        //this.recycler = findViewById(R.id.recycler_view_books)

    }

     override fun setDataRecyclerView(items: List<Book>) {
        this.listview.adapter = BookListAdaptater(this,items);

        println(items);
    }
}