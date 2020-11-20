package com.groupe3.librairiedehenripotier.presenter

import com.groupe3.librairiedehenripotier.api.HenriPotierData
import com.groupe3.librairiedehenripotier.model.Book
import com.groupe3.librairiedehenripotier.view.View
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BookListPresenter (private var view: View, private val api: HenriPotierData): Presenter {

     fun onResume() {
         GlobalScope.launch(Dispatchers.Main) {
             val listBooks = api.listBooks()
             view.setDataView(listBooks)
         }
    }

    override suspend fun getBookList(): List<Book> {
        return api.listBooks();
    }
}