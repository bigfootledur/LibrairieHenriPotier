package com.groupe3.librairiedehenripotier.view

import com.groupe3.librairiedehenripotier.model.Book

interface BookListView {
    fun setDataView(items: List<Book>)
}