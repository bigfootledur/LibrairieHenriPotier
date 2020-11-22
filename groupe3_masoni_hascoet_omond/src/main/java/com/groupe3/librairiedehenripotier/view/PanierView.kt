package com.groupe3.librairiedehenripotier.view

import com.groupe3.librairiedehenripotier.model.Book

interface PanierView {
    fun showPayerToast()
    fun setMontantPrix(price: Float)
    fun setMontantPromotion(promotion: Float)
    fun removeBook(book: Book)
}