package com.groupe3.librairiedehenripotier.presenter

import com.groupe3.librairiedehenripotier.api.HenriPotierData
import com.groupe3.librairiedehenripotier.view.BookListView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BookListPresenter (private var view: BookListView, private val api: HenriPotierData) {

     fun getListBooks() {
         GlobalScope.launch(Dispatchers.Main) {
             val listBooks = api.listBooks()
             view.setDataView(listBooks)
         }
    }
}