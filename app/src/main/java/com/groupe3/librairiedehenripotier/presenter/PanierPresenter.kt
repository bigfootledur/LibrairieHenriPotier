package com.groupe3.librairiedehenripotier.presenter

import com.groupe3.librairiedehenripotier.api.HenriPotierData
import com.groupe3.librairiedehenripotier.model.Book
import com.groupe3.librairiedehenripotier.utils.OfferCalculator
import com.groupe3.librairiedehenripotier.view.PanierView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

                val (bestOffer, price) = OfferCalculator.getBestOfferWithPrice(books, commercialOffers)
                val promotion = bestOffer?.value ?: 0f

                view.apply {
                    setMontantPrix(price)
                    setMontantPromotion(promotion)
                }
            }
        }
    }
}