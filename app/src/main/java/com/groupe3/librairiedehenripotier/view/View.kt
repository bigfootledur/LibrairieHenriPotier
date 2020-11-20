package com.groupe3.librairiedehenripotier.view

import com.groupe3.librairiedehenripotier.model.Book

interface View {
    fun setDataRecyclerView(items: ArrayList<Book>):Unit
}