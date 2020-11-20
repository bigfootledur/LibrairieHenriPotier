package com.groupe3.librairiedehenripotier.view

import com.groupe3.librairiedehenripotier.model.Book

interface View {
    fun setDataView(items: List<Book>):Unit
}