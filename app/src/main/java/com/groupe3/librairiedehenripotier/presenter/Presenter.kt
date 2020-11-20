package com.groupe3.librairiedehenripotier.presenter

import com.groupe3.librairiedehenripotier.model.Book


interface Presenter {
    fun getBookList():List<Book>
}