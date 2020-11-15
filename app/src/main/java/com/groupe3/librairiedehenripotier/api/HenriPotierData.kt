package com.groupe3.librairiedehenripotier.api

import com.groupe3.librairiedehenripotier.model.Book
import com.groupe3.librairiedehenripotier.model.CommercialOffer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HenriPotierData {

    private var service: HenriPotierAPI

    private const val HENRI_POTIER_BASE_URL = "http://henri-potier.xebia.fr/"

    init {
        val retrofit = Retrofit.Builder().baseUrl(HENRI_POTIER_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(HenriPotierAPI::class.java)
    }

    suspend fun listBooks() : List<Book> {
        return service.listBooks()
    }

    suspend fun commercialOffers(bookIds: List<String>): List<CommercialOffer> {
        val bookIdsStr = bookIds.joinToString(",")

        print(bookIdsStr)

        return service.commercialOffer(bookIdsStr).offers
    }
}
