package com.groupe3.librairiedehenripotier.utils

import com.groupe3.librairiedehenripotier.model.Book
import com.groupe3.librairiedehenripotier.model.CommercialOffer
import com.groupe3.librairiedehenripotier.model.OfferType
import kotlin.math.floor

object OfferCalculator {

    fun getBestOfferWithPrice(books: List<Book>, offers: List<CommercialOffer>): Pair<CommercialOffer, Float> {
        val priceWithOffers = offers.map { offer -> getPriceWithOffer(books, offer) }
        val bestOfferIdx = priceWithOffers.indexOf(priceWithOffers.min())
        return Pair(offers[bestOfferIdx], priceWithOffers[bestOfferIdx])
    }

    fun getPriceWithOffer(books: List<Book>, offer: CommercialOffer): Float {
        return when(offer.type) {
            OfferType.PERCENTAGE -> percentage(books, offer.value)
            OfferType.MINUS -> minus(books, offer.value)
            OfferType.SLICE -> slice(books, offer.value, offer.sliceValue)
        }
    }

    private fun percentage(books: List<Book>, value: Float): Float {
        return books.map{book -> book.price}.sum() * (value / 100)
    }

    private fun minus(books: List<Book>, value: Float): Float {
        return books.map{book -> book.price}.sum() - value
    }

    private fun slice(books: List<Book>, value: Float, sliceValue: Float?): Float {
        if (sliceValue == null) throw Error("No slice value")
        val priceSum = books.map{book -> book.price}.sum()
        return priceSum - (floor(priceSum / sliceValue) * value)
    }

}