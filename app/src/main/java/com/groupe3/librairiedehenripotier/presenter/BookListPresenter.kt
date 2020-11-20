package com.groupe3.librairiedehenripotier.presenter

import com.groupe3.librairiedehenripotier.api.HenriPotierAPI
import com.groupe3.librairiedehenripotier.model.Book
import com.groupe3.librairiedehenripotier.view.View

class BookListPresenter (private var view: View, private val api: HenriPotierAPI): Presenter {

    fun onResume() {
           //https://github.com/antoniolg/androidmvp/blob/master/appkotlin/src/main/java/antonioleiva/com/appkotlin/main/MainPresenter.kt
        // http://henri-potier.xebia.fr/books
    }

    override fun getBookList(): List<Book> {
       return ArrayList()
    }
}