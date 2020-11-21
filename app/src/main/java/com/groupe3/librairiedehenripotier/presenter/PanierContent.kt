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

    fun removeBook(book: Book) {
        listBooks.remove(book)
    }

    fun getBooksWithQuantity(): ArrayList<Pair<Book, Int>> {
        val uniqueBooks = listBooks.toSet()
        val booksWithQuantity = ArrayList<Pair<Book, Int>>()
        for (item in uniqueBooks) {
            booksWithQuantity.add(Pair(item, getQuantityBook(item)))
        }
        return booksWithQuantity
    }

    private fun getQuantityBook(book: Book): Int {
        return listBooks.count{ b -> b.isbn == book.isbn}
    }
}