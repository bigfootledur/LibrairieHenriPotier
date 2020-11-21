package com.groupe3.librairiedehenripotier.presenter

import com.groupe3.librairiedehenripotier.model.Book

object PanierContent {
    private val listBooks = ArrayList<Book>()

    fun addBook(book: Book) {
        this.listBooks.add(book)
    }

    fun getBooks(): List<Book> {
        return listBooks
    }
}