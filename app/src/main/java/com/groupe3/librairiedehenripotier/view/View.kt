package com.groupe3.librairiedehenripotier.view

import com.groupe3.librairiedehenripotier.model.Book

interface View {
    fun setItems(items: List<Book>):Unit
}