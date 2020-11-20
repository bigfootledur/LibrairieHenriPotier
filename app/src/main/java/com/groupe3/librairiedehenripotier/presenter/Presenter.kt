package com.groupe3.librairiedehenripotier.presenter

import com.groupe3.librairiedehenripotier.model.Book


interface Presenter {
    suspend fun getBookList():List<Book>
}