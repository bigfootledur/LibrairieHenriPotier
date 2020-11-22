package com.groupe3.librairiedehenripotier.presenter

import com.groupe3.librairiedehenripotier.api.HenriPotierData
import com.groupe3.librairiedehenripotier.model.Book
import com.groupe3.librairiedehenripotier.utils.OfferCalculator
import com.groupe3.librairiedehenripotier.view.PanierView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.round

class PanierPresenter (private var view: PanierView, private val api: HenriPotierData)  {
    fun getPriceAndPromotion(books: List<Book>) {
        if(books.isEmpty()) {
            view.apply{
                setMontantPrix(0f)
                setMontantPromotion(0f)
            }
        }
        else {
            GlobalScope.launch(Dispatchers.Main) {
                val bookIds = books.map { book -> book.isbn }
                val commercialOffers = api.commercialOffers(bookIds)

                val (_, price) = OfferCalculator.getBestOfferWithPrice(books, commercialOffers)
                val promotion = computePromotion(books, price)

                view.apply {
                    setMontantPrix(price)
                    setMontantPromotion(promotion)
                }
            }
        }
    }

    private fun computePromotion(books: List<Book>, loweredPrice: Float): Float {
        val initialPrice = books.map{book -> book.price}.sum()
        val promotion = initialPrice - loweredPrice

        // Round to 2 decimals
        return round(promotion * 100)/100
    }
}