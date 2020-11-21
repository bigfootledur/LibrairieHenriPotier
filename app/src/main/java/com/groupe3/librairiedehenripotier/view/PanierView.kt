package com.groupe3.librairiedehenripotier.view

interface PanierView {
    fun showPayerToast()
    fun setMontantPrix(price: Float)
    fun setMontantPromotion(promotion: Float)
    fun setLivreAchetes(number: String)
}